package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.dto.NotificationDTO;
import com.ulbs.careerstartup.service.NotificationService;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
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
    public Collection<NotificationDTO> findAllNotifications() {
        return notificationService.findAllNotifications();
    }

    @GetMapping("/{id}")
    public NotificationDTO findNotificationById(@PathVariable UUID id) {
        return notificationService.findNotificationById(id);
    }

    @GetMapping(BY_CRITERIA)
    public Collection<NotificationDTO> findByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return notificationService.findByCriteria(criteria);
    }

    @PostMapping
    public NotificationDTO saveNotification(@RequestBody NotificationDTO notificationDTO) {
        return notificationService.saveNotification(notificationDTO);
    }

    @PatchMapping
    public NotificationDTO updateNotification(@RequestBody NotificationDTO notificationDTO) {
        return notificationService.updateNotification(notificationDTO);
    }

    @DeleteMapping
    public void deleteNotification(@RequestBody NotificationDTO notificationDTO) {
        notificationService.deleteNotification(notificationDTO);
    }
}
