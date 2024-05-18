package com.ulbs.careerstartup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Notification content is required")
    @NotEmpty(message = "Notification content is required")
    private String notificationContent;

    @NotNull(message = "Date is required")
    @NotEmpty(message = "Date is required")
    private String date;

    @NotNull(message = "Seen is required")
    @NotEmpty(message = "Seen is required")
    @JsonProperty("user")
    private UserDTO userDTO;
}
