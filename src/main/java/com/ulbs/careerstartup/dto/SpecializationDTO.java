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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecializationDTO that = (SpecializationDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(startedDate, that.startedDate) && Objects.equals(finishedDate, that.finishedDate) && Objects.equals(degree, that.degree) && Objects.equals(coursesDTO, that.coursesDTO) && Objects.equals(userDTO, that.userDTO) && Objects.equals(facultyDTO, that.facultyDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startedDate, finishedDate, degree, coursesDTO, userDTO, facultyDTO);
    }
}
