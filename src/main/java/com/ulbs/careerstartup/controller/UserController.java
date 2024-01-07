package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.dto.*;
import com.ulbs.careerstartup.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
public class UserController {

    private UserService userService;

    @GetMapping
    public ResponseEntity<Collection<UserDTO>> findAllUsers() {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUserDTO = userService.saveUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDTO);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        UserDTO userDTO = userService.findByEmail(email);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/{email}/languages")
    public ResponseEntity<Collection<LanguageDTO>> getUserLanguagesByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserLanguages(email));
    }

    @GetMapping("/{email}/specializations")
    public ResponseEntity<Collection<SpecializationDTO>> getUserSpecializationsByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserSpecialization(email));
    }

    @GetMapping("/{email}/skills")
    public ResponseEntity<Collection<SpecializationDTO>> getUserSkillsByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserSkills(email));
    }

    @GetMapping("/{email}/events-created")
    public ResponseEntity<Collection<EventDTO>> getUserCreatedEventsByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserEventCreated(email));
    }

    @GetMapping("/subscribed-events")
    public ResponseEntity<Collection<EventDTO>> getUserEventsSubscribedByEmail() {
        return ResponseEntity.ok(userService.getUserEventSubscribed());
    }

    @GetMapping("/{email}/bibliography")
    public ResponseEntity<Collection<BibliographyDTO>> findUserBibliographyByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserBibliography(email));
    }

    @GetMapping("/{email}/jobs-candidate")
    public ResponseEntity<Collection<JobCandidatesDTO>> getUserJobsCandidateByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserJobsCandidate(email));
    }

    @GetMapping("/{email}/jobs-history")
    public ResponseEntity<Collection<JobHistoryDTO>> getUserJobsHistoryByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserJobsHistory(email));
    }

    @GetMapping("/notifications")
    public ResponseEntity<Collection<NotificationDTO>> getUserNotificationsByEmail() {
        return ResponseEntity.ok(userService.getUserNotifications());
    }

    @GetMapping("/sent-messages")
    public ResponseEntity<Collection<MessageDTO>> getUserSentMessagesByEmail() {
        return ResponseEntity.ok(userService.getUserSentMessages());
    }

    @GetMapping("/received-messages")
    public ResponseEntity<Collection<MessageDTO>> getUserReceivedMessagesByEmail() {
        return ResponseEntity.ok(userService.getUserReceivedMessages());
    }

    @GetMapping("/{email}/written-referrals")
    public ResponseEntity<Collection<ReferralDTO>> getUserWrittenReferralsByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserWrittenReferrals(email));
    }

    @GetMapping("/{email}/received-referrals")
    public ResponseEntity<Collection<ReferralDTO>> getUserReceivedReferralsByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserReceivedReferrals(email));
    }
}
