package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.dto.NotificationDTO;
import com.ulbs.careerstartup.service.NotificationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/notifications")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "Notification", description = "The Notification API")
public class NotificationController {

    private NotificationService notificationService;

    @GetMapping
    public Collection<NotificationDTO> findAllNotifications() {
        return notificationService.findAllNotifications();
    }

    @GetMapping("/{id}")
    public NotificationDTO findNotificationById(@PathVariable UUID id) {
        return notificationService.findNotificationById(id);
    }

    @PostMapping
    public NotificationDTO saveNotification(@RequestBody NotificationDTO notificationDTO) {
        return notificationService.saveNotification(notificationDTO);
    }

    @PatchMapping
    public NotificationDTO updateNotification(@RequestBody NotificationDTO notificationDTO) {
        return notificationService.updateNotification(notificationDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteNotification(@PathVariable UUID id) {
        notificationService.deleteNotification(id);
    }
}
