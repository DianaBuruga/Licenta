package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.dto.NotificationDTO;
import com.ulbs.careerstartup.service.NotificationService;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.Constants.BY_CRITERIA;

@RestController
@AllArgsConstructor
@RequestMapping("/notifications")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "Notification", description = "The Notification API")
public class NotificationController {

    private NotificationService notificationService;

    @GetMapping
    public ResponseEntity<Collection<NotificationDTO>> findAllNotifications() {
        return ResponseEntity.ok(notificationService.findAllNotifications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(notificationService.findById(id));
    }

    @GetMapping(BY_CRITERIA)
    public Collection<NotificationDTO> findNotificationsByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return notificationService.findNotificationsByCriteria(criteria);
    }

    @PostMapping
    public ResponseEntity<NotificationDTO> saveNotification(@RequestBody NotificationDTO notificationDTO) {
        return ResponseEntity.ok(notificationService.saveNotification(notificationDTO));
    }

    @PatchMapping
    public ResponseEntity<NotificationDTO> updateNotification(@RequestBody NotificationDTO notificationDTO) {
        return ResponseEntity.ok(notificationService.updateNotification(notificationDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteNotification(@RequestBody NotificationDTO notificationDTO) {
        notificationService.deleteNotification(notificationDTO);
        return ResponseEntity.noContent().build();
    }
}
