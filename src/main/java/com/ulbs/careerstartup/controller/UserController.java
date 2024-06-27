package com.ulbs.careerstartup.controller;

import com.lowagie.text.DocumentException;
import com.ulbs.careerstartup.apidoc.UserApiDoc;
import com.ulbs.careerstartup.constant.FileType;
import com.ulbs.careerstartup.dto.FileDTO;
import com.ulbs.careerstartup.dto.UserDTO;
import com.ulbs.careerstartup.entity.pk.FilePK;
import com.ulbs.careerstartup.service.FileService;
import com.ulbs.careerstartup.service.UserService;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Collection;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.Constants.ATTACHMENT_FILENAME;
import static com.ulbs.careerstartup.constant.Constants.INLINE_FILENAME;
import static org.apache.tika.metadata.TikaMetadataKeys.RESOURCE_NAME_KEY;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
@Tag(name = "User", description = "The User API")
public class UserController implements UserApiDoc {

    private static final String TABLE_NAME = "user";
    private UserService userService;
    private FileService fileService;

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

    @GetMapping(value = "/userinfo", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public UserDTO getAuthenticatedUser(Principal principal) {
        return userService.findByEmail(principal.getName());
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public Collection<UserDTO> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public UserDTO findUserById(@PathVariable UUID id) {
        return userService.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public UserDTO saveUser(@RequestBody UserDTO userDTO) {
        return userService.saveUser(userDTO);
    }

    @PatchMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    @PostMapping(value = "/profile-photo")
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public void saveProfilePhoto(@RequestParam UUID id, @RequestParam MultipartFile multipartFile) throws IOException {
        FilePK filePK = FilePK.builder().tableId(id).tableName(TABLE_NAME).type(FileType.PROFILE_PHOTO).build();
        fileService.uploadFile(filePK, multipartFile);
    }

    @GetMapping(value = "/profilePhoto/download/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public ResponseEntity<Resource> downloadFileById(@PathVariable UUID id) {
        FilePK filePK = FilePK.builder().tableId(id).tableName(TABLE_NAME).type(FileType.PROFILE_PHOTO).build();

        FileDTO fileDTO = fileService.findFileById(filePK);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, ATTACHMENT_FILENAME + fileDTO.getName()).body(new ByteArrayResource(fileDTO.getContent()));
    }

    @GetMapping(value = "/profilePhoto/view/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Resource> viewFileById(@PathVariable UUID id) {
        FilePK filePK = FilePK.builder().tableId(id).tableName(TABLE_NAME).type(FileType.PROFILE_PHOTO).build();

        FileDTO fileDTO = fileService.findFileById(filePK);

        return ResponseEntity.ok().contentType(detectMimeType(fileDTO.getContent(), fileDTO.getName())).header(HttpHeaders.CONTENT_DISPOSITION, INLINE_FILENAME + fileDTO.getName()).body(new ByteArrayResource(fileDTO.getContent()));
    }

    @GetMapping(value = "/CV/{id}")
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public ResponseEntity<Resource> exportUserPdf(@PathVariable UUID id) throws IOException, DocumentException {
        FileDTO fileDTO = userService.generateCV(id);

        return ResponseEntity.ok().contentType(detectMimeType(fileDTO.getContent(), fileDTO.getName())).header(HttpHeaders.CONTENT_DISPOSITION, INLINE_FILENAME + fileDTO.getName()).body(new ByteArrayResource(fileDTO.getContent()));
    }

    @DeleteMapping(value = "/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public void deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
    }

    @GetMapping(value = "/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','ADMIN')")
    public UserDTO findUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }
}
