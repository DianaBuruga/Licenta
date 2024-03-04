/*
package com.ulbs.careerstartup.mapper;

import com.ulbs.careerstartup.dto.NotificationDTO;
import com.ulbs.careerstartup.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface NotificationMapper {

    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    @Mapping(source = "user", target = "userDTO")
    NotificationDTO notificationToNotificationDTO(Notification notification);

    @Mapping(source = "userDTO", target = "user")
    Notification notificationDTOToNotification(NotificationDTO notificationDTO);
}*/
