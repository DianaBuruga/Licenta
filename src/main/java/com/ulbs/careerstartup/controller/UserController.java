package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.UserApiDoc;
import com.ulbs.careerstartup.constant.FileType;
import com.ulbs.careerstartup.dto.*;
import com.ulbs.careerstartup.entity.pk.FilePK;
import com.ulbs.careerstartup.service.FileService;
import com.ulbs.careerstartup.service.UserService;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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
    public Collection<UserDTO> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public UserDTO findUserById(@PathVariable UUID id) {
        return userService.findById(id);
    }

    @GetMapping(BY_CRITERIA)
    public Collection<UserDTO> findByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return userService.findUsersByCriteria(criteria);
    }

    @PostMapping
    public UserDTO saveUser(@RequestBody UserDTO userDTO, @RequestBody MultipartFile multipartFile) throws IOException {
        UserDTO savedUserDTO = userService.saveUser(userDTO);

        FilePK filePK = FilePK.builder()
                .tableId(savedUserDTO.getId())
                .tableName(TABLE_NAME).build();

        FileDTO fileDTO = fileService.uploadOrFindFile(multipartFile, filePK, FileType.PROFILE_PHOTO);

        savedUserDTO.setProfilePhoto(fileDTO);

        return savedUserDTO;
    }

    @PatchMapping
    public UserDTO updateUser(@RequestBody UserDTO userDTO, @RequestBody MultipartFile multipartFile) throws IOException {
        UserDTO updatedUserDTO = userService.updateUser(userDTO);

        FilePK filePK = FilePK.builder()
                .tableId(updatedUserDTO.getId())
                .tableName(TABLE_NAME).build();

        FileDTO fileDTO = fileService.uploadOrFindFile(multipartFile, filePK, FileType.PROFILE_PHOTO);

        updatedUserDTO.setProfilePhoto(fileDTO);

        return updatedUserDTO;
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

    @DeleteMapping("/{email}")
    public void deleteUser(@RequestBody UserDTO userDTO) {
        userService.deleteUser(userDTO);
    }

    @GetMapping("/{email}")
    public UserDTO findUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/{email}/languages")
    public Collection<LanguageDTO> findUserLanguagesByEmail(@PathVariable String email) {
        return userService.findUserLanguages(email);
    }

    @GetMapping("/{email}/specializations")
    public Collection<SpecializationDTO> findUserSpecializationsByEmail(@PathVariable String email) {
        return userService.findUserSpecialization(email);
    }

    @GetMapping("/{email}/skills")
    public Collection<SpecializationDTO> findUserSkillsByEmail(@PathVariable String email) {
        return userService.findUserSkills(email);
    }

    @GetMapping("/{email}/events-created")
    public Collection<EventDTO> findUserCreatedEventsByEmail(@PathVariable String email) {
        return userService.findUserEventCreated(email);
    }

    @GetMapping("/{email}/subscribed-events")
    public Collection<EventDTO> findUserEventsSubscribedByEmail(@PathVariable String email) {
        return userService.findUserEventSubscribed();
    }

    @GetMapping("/{email}/bibliography")
    public Collection<BibliographyDTO> findUserBibliographyByEmail(@PathVariable String email) {
        return userService.findUserBibliography(email);
    }

    @GetMapping("/{email}/jobs-candidate")
    public Collection<JobCandidatesDTO> findUserJobsCandidateByEmail(@PathVariable String email) {
        return userService.findUserJobsCandidate(email);
    }

    @GetMapping("/{email}/jobs-history")
    public Collection<JobHistoryDTO> findUserJobsHistoryByEmail(@PathVariable String email) {
        return userService.findUserJobsHistory(email);
    }

    @GetMapping("/{email}/notifications")
    public Collection<NotificationDTO> findUserNotificationsByEmail(@PathVariable String email) {
        return userService.findUserNotifications();
    }

    @GetMapping("/{email}/sent-messages")
    public Collection<MessageDTO> findUserSentMessagesByEmail(@PathVariable String email) {
        return userService.findUserSentMessages();
    }

    @GetMapping("/{email}/received-messages")
    public Collection<MessageDTO> findUserReceivedMessagesByEmail(@PathVariable String email) {
        return userService.findUserReceivedMessages();
    }

    @GetMapping("/{email}/written-referrals")
    public Collection<ReferralDTO> findUserWrittenReferralsByEmail(@PathVariable String email) {
        return userService.findUserWrittenReferrals(email);
    }

    @GetMapping("/{email}/received-referrals")
    public Collection<ReferralDTO> findUserReceivedReferralsByEmail(@PathVariable String email) {
        return userService.findUserReceivedReferrals(email);
    }
}
