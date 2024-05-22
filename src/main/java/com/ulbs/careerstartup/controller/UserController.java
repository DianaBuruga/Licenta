package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.UserApiDoc;
import com.ulbs.careerstartup.constant.FileType;
import com.ulbs.careerstartup.dto.FileDTO;
import com.ulbs.careerstartup.dto.UserDTO;
import com.ulbs.careerstartup.entity.pk.FilePK;
import com.ulbs.careerstartup.service.FileService;
import com.ulbs.careerstartup.service.UserService;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import com.ulbs.careerstartup.util.UserPdfExporter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.Constants.*;
import static org.apache.tika.metadata.TikaMetadataKeys.RESOURCE_NAME_KEY;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
//@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
//@CrossOrigin(origins = "http://localhost:4200",
//        allowedHeaders = { ORIGIN, CONTENT_TYPE, ACCEPT, AUTHORIZATION, ACCESS_CONTROL_ALLOW_ORIGIN},
//    methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS, RequestMethod.PATCH },
//        maxAge = 3600
//)
@Tag(name = "User", description = "The User API")
public class UserController implements UserApiDoc {

    private static final String TABLE_NAME = "user";
    private UserService userService;
    private FileService fileService;

    @GetMapping(value="/userinfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO getAuthenticatedUser(@AuthenticationPrincipal OidcUser oidcUser, Principal principal) {
        return userService.findByEmail(oidcUser.getEmail());
    }

    @GetMapping
    public Collection<UserDTO> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO findUserById(@PathVariable UUID id) {
        return userService.findById(id);
    }

    @GetMapping(value = BY_CRITERIA, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<UserDTO> findByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return userService.findByCriteria(criteria);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO saveUser(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    @PostMapping(value = "/profile-photo")
    public void saveProfilePhoto(@RequestParam UUID id, @RequestParam MultipartFile multipartFile) throws IOException {
        FilePK filePK = FilePK.builder()
                .tableId(id)
                .tableName(TABLE_NAME).build();
        fileService.uploadFile(FileType.PROFILE_PHOTO, filePK, multipartFile);
    }

    @GetMapping(value = "/id/{id}/profilePhoto/download/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource> downloadFileById(@PathVariable UUID id) throws FileNotFoundException {
        FilePK filePK = FilePK.builder()
                .tableId(id)
                .tableName(TABLE_NAME).build();

        FileDTO fileDTO = fileService.findFileById(filePK);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, ATTACHMENT_FILENAME + fileDTO.getName())
                .body(new ByteArrayResource(fileDTO.getContent()));
    }

    @GetMapping(value = "/{id}/profilePhoto/view/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource> viewFileById(@PathVariable UUID id) throws FileNotFoundException {
        FilePK filePK = FilePK.builder()
                .tableId(id)
                .tableName(TABLE_NAME).build();

        FileDTO fileDTO = fileService.findFileById(filePK);

        return ResponseEntity.ok()
                .contentType(detectMimeType(fileDTO.getContent(), fileDTO.getName()))
                .header(HttpHeaders.CONTENT_DISPOSITION, INLINE_FILENAME + fileDTO.getName())
                .body(new ByteArrayResource(fileDTO.getContent()));
    }

    public static MediaType detectMimeType(byte[] data, String filename) {
        try {
            InputStream is = new ByteArrayInputStream(data);
            AutoDetectParser parser = new AutoDetectParser();
            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            metadata.set(RESOURCE_NAME_KEY, filename);
            parser.parse(is, handler, metadata);
            return MediaType.parseMediaType(metadata.get(CONTENT_TYPE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MediaType.APPLICATION_OCTET_STREAM;
    }

    @GetMapping(value = "/CV/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource> exportUserPdf(@PathVariable UUID id) {
        UserPdfExporter userPdfExporter = new UserPdfExporter();
        UserDTO userDTO = userService.findById(id);
        userPdfExporter.generateCv(userDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUser(@RequestBody UserDTO userDTO) {
        userService.deleteUser(userDTO);
    }

    @GetMapping(value = "/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO findUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }
}
