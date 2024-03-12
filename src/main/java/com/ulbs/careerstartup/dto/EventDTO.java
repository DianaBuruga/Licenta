package com.ulbs.careerstartup.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
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
}
