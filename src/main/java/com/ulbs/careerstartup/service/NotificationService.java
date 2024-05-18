package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.dto.NotificationDTO;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.NotificationRepository;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class NotificationService {
    private NotificationRepository notificationRepository;
    private Mapper mapper;

    public Collection<NotificationDTO> findAllNotifications() {
        return notificationRepository
                .findAll()
                .stream()
                .map(mapper::notificationToNotificationDTO)
                .toList();
    }

    public NotificationDTO findNotificationById(UUID id) {
        return notificationRepository.findById(id)
                .map(mapper::notificationToNotificationDTO)
                .orElseThrow(() -> new EntityNotFoundException("Notification with id " + id + " not found"));
    }

    public Collection<NotificationDTO> findByCriteria(List<SearchCriteria> criteria) {
        return notificationRepository
                .findAll(new GenericSpecification<>(criteria), PageRequest.of(0, 10))
                .map(mapper::notificationToNotificationDTO)
                .toList();
    }

    public NotificationDTO saveNotification(NotificationDTO notificationDTO) {
        return mapper.notificationToNotificationDTO(notificationRepository.save(mapper.notificationDTOToNotification(notificationDTO)));
    }

    public NotificationDTO updateNotification(NotificationDTO notificationDTO) {
        return mapper.notificationToNotificationDTO(notificationRepository.save(mapper.notificationDTOToNotification(notificationDTO)));
    }

    public void deleteNotification(NotificationDTO notificationDTO) {
        notificationRepository.delete(mapper.notificationDTOToNotification(notificationDTO));
    }
}
