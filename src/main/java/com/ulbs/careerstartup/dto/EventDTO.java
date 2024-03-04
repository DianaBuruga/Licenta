package com.ulbs.careerstartup.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EventDTO {

    private UUID id;

    @NotNull
    private String description;

    @NotNull
    private Timestamp date;

    @NotNull
    private String location;

    @NotNull
    private UserDTO creatorDTO;

    private FileDTO fileDTO;

    @NotNull
    private Collection<UserDTO> subscribersDTO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventDTO eventDTO = (EventDTO) o;
        return Objects.equals(id, eventDTO.id) && Objects.equals(description, eventDTO.description) && Objects.equals(date, eventDTO.date) && Objects.equals(location, eventDTO.location) && Objects.equals(creatorDTO, eventDTO.creatorDTO) && Objects.equals(subscribersDTO, eventDTO.subscribersDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, date, location, creatorDTO, subscribersDTO);
    }
}
