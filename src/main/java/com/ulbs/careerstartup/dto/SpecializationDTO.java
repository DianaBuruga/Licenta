package com.ulbs.careerstartup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ulbs.careerstartup.constant.Degree;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Collection;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SpecializationDTO {

    private UUID id;

    @NotNull(message = "Started date is required")
    @NotEmpty(message = "Started date is required")
    private String startedDate;

    private String finishedDate;

    @NotNull(message = "Degree is required")
    private Degree degree;

    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    private String name;

    @JsonProperty("courses")
    private Collection<CourseDTO> coursesDTO;

    @NotNull(message = "User is required")
    @JsonProperty("user")
    private UserDTO userDTO;

    @NotNull(message = "Faculty is required")
    @JsonProperty("faculty")
    private FacultyDTO facultyDTO;
}
