package com.ulbs.careerstartup.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class NotificationDTO {

    private UUID id;

    @NotNull
    private String notificationContent;

    @NotNull
    private Timestamp date;

    @NotNull
    private UserDTO userDTO;
}
