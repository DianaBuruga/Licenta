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
public class SpecializationDTO {

    private UUID id;

    @NotNull
    private Timestamp startedDate;

    @NotNull
    private Timestamp finishedDate;

    @NotNull
    private String degree;

    @NotNull
    private Collection<CourseDTO> coursesDTO;

    @NotNull
    private UserDTO userDTO;

    @NotNull
    private FacultyDTO facultyDTO;
}
