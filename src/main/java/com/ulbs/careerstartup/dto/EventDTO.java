package com.ulbs.careerstartup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Title is required")
    @NotEmpty(message = "Title is required")
    private String description;

    @NotNull(message = "Date is required")
    @NotEmpty(message = "Date is required")
    private String date;

    @NotNull(message = "Location is required")
    @NotEmpty(message = "Location is required")
    private String location;

    @NotNull(message = "Creator is required")
    @NotEmpty(message = "Creator is required")
    @JsonProperty("creator")
    private UserDTO creatorDTO;

    @JsonProperty("file")
    private FileDTO fileDTO;

    @NotNull(message = "Subscribers are required")
    @NotEmpty(message = "Subscribers are required")
    @JsonProperty("subscribers")
    private Collection<UserDTO> subscribersDTO;
}
