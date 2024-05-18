package com.ulbs.careerstartup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ReferralDTO {

    private UUID id;

    @NotNull(message = "Description is required")
    @NotEmpty(message = "Description is required")
    private String description;

    @NotNull(message = "Teacher is required")
    @NotEmpty(message = "Teacher is required")
    @JsonProperty("teacher")
    private UserDTO teacherDTO;

    @NotNull(message = "Student is required")
    @NotEmpty(message = "Student is required")
    @JsonProperty("student")
    private UserDTO studentDTO;
}
