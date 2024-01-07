package com.ulbs.careerstartup.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NotificationDTO {

    private UUID id;

    @NotNull
    private String notificationContent;

    @NotNull
    private Timestamp date;

    @NotNull
    private UserDTO userDTO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationDTO that = (NotificationDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(notificationContent, that.notificationContent) && Objects.equals(date, that.date) && Objects.equals(userDTO, that.userDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, notificationContent, date, userDTO);
    }
}
