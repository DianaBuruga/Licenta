package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.UserApiDoc;
import com.ulbs.careerstartup.constant.FileType;
import com.ulbs.careerstartup.dto.*;
import com.ulbs.careerstartup.entity.pk.FilePK;
import com.ulbs.careerstartup.service.FileService;
import com.ulbs.careerstartup.service.UserService;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import com.ulbs.careerstartup.util.UserPdfExporter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.Constants.*;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "User", description = "The User API")
public class UserController implements UserApiDoc {

    private static final String TABLE_NAME = "user";
    private UserService userService;
    private FileService fileService;

    @GetMapping
    public ResponseEntity<Collection<UserDTO>> findAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping(BY_CRITERIA)
    public ResponseEntity<Collection<UserDTO>> findUsersByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return ResponseEntity.ok(userService.findUsersByCriteria(criteria));
    }
    
    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO, @RequestBody MultipartFile multipartFile) throws IOException {
        UserDTO savedUserDTO = userService.saveUser(userDTO);

        FilePK filePK = FilePK.builder()
                .tableId(savedUserDTO.getId())
                .tableName(TABLE_NAME).build();

        FileDTO fileDTO = fileService.uploadOrFindFile(multipartFile, filePK, FileType.PROFILE_PHOTO);

        savedUserDTO.setProfilePhoto(fileDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDTO);
    }
    
    @PatchMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @RequestBody MultipartFile multipartFile) throws IOException {
        UserDTO updatedUserDTO = userService.updateUser(userDTO);

        FilePK filePK = FilePK.builder()
                .tableId(updatedUserDTO.getId())
                .tableName(TABLE_NAME).build();

        FileDTO fileDTO = fileService.uploadOrFindFile(multipartFile, filePK, FileType.PROFILE_PHOTO);

        updatedUserDTO.setProfilePhoto(fileDTO);

        return ResponseEntity.ok(updatedUserDTO);
    }

    @GetMapping("/{id}/file/download/")
    public ResponseEntity<Resource> downloadFileById(@PathVariable UUID id) throws FileNotFoundException {
        FilePK filePK = FilePK.builder()
                .tableId(id)
                .tableName(TABLE_NAME).build();

        FileDTO fileDTO = fileService.findFileById(filePK);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, ATTACHMENT_FILENAME + fileDTO.getName())
                .body(new ByteArrayResource(fileDTO.getContent()));
    }

    @GetMapping("/{id}/file/view/")
    public ResponseEntity<Resource> viewFileById(@PathVariable UUID id) throws FileNotFoundException {
        FilePK filePK = FilePK.builder()
                .tableId(id)
                .tableName(TABLE_NAME).build();

        FileDTO fileDTO = fileService.findFileById(filePK);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, INLINE_FILENAME + fileDTO.getName())
                .body(new ByteArrayResource(fileDTO.getContent()));
    }

    @GetMapping("/export-pdf")
    public ResponseEntity<Resource> exportUserPdf(@RequestBody UserDTO userDTO) {
        UserPdfExporter pdfExporter = new UserPdfExporter();

        String fileName = userDTO.getName() + "CV.pdf";
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, INLINE_FILENAME + fileName)
                .body( new ByteArrayResource(pdfExporter.exportUserToPdf(userDTO, fileName)));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUser(@RequestBody UserDTO userDTO) {
        userService.deleteUser(userDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> findUserByEmail(@PathVariable String email) {
        UserDTO userDTO = userService.findByEmail(email);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/{email}/languages")
    public ResponseEntity<Collection<LanguageDTO>> findUserLanguagesByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findUserLanguages(email));
    }

    @GetMapping("/{email}/specializations")
    public ResponseEntity<Collection<SpecializationDTO>> findUserSpecializationsByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findUserSpecialization(email));
    }

    @GetMapping("/{email}/skills")
    public ResponseEntity<Collection<SpecializationDTO>> findUserSkillsByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findUserSkills(email));
    }

    @GetMapping("/{email}/events-created")
    public ResponseEntity<Collection<EventDTO>> findUserCreatedEventsByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findUserEventCreated(email));
    }

    @GetMapping("/{email}/subscribed-events")
    public ResponseEntity<Collection<EventDTO>> findUserEventsSubscribedByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findUserEventSubscribed());
    }

    @GetMapping("/{email}/bibliography")
    public ResponseEntity<Collection<BibliographyDTO>> findUserBibliographyByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findUserBibliography(email));
    }

    @GetMapping("/{email}/jobs-candidate")
    public ResponseEntity<Collection<JobCandidatesDTO>> findUserJobsCandidateByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findUserJobsCandidate(email));
    }

    @GetMapping("/{email}/jobs-history")
    public ResponseEntity<Collection<JobHistoryDTO>> findUserJobsHistoryByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findUserJobsHistory(email));
    }

    @GetMapping("/{email}/notifications")
    public ResponseEntity<Collection<NotificationDTO>> findUserNotificationsByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findUserNotifications());
    }

    @GetMapping("/{email}/sent-messages")
    public ResponseEntity<Collection<MessageDTO>> findUserSentMessagesByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findUserSentMessages());
    }

    @GetMapping("/{email}/received-messages")
    public ResponseEntity<Collection<MessageDTO>> findUserReceivedMessagesByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findUserReceivedMessages());
    }

    @GetMapping("/{email}/written-referrals")
    public ResponseEntity<Collection<ReferralDTO>> findUserWrittenReferralsByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findUserWrittenReferrals(email));
    }

    @GetMapping("/{email}/received-referrals")
    public ResponseEntity<Collection<ReferralDTO>> findUserReceivedReferralsByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findUserReceivedReferrals(email));
    }
}
