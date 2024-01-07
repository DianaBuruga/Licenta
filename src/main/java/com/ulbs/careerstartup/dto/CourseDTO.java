package com.ulbs.careerstartup.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.ValidationMessages.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CourseDTO {

    private UUID id;

    @NotNull
    private SpecializationDTO specializationDTO;

    @NotNull
    private Collection<SkillDTO> skillsDTO;

    @NotNull
    @Size(max = 45, message = INPUT_TOO_LONG)
    private String name;

    @NotNull
    @Max(value = 6, message = INVALID_YEAR_OF_STUDY)
    @Min(value = 1, message = INVALID_YEAR_OF_STUDY)
    private Integer year;

    @NotNull
    @Max(value = 2, message = INVALID_SEMESTER)
    @Min(value = 1, message = INVALID_SEMESTER)
    private Integer semester;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseDTO courseDTO = (CourseDTO) o;
        return Objects.equals(id, courseDTO.id) && Objects.equals(specializationDTO, courseDTO.specializationDTO) && Objects.equals(skillsDTO, courseDTO.skillsDTO) && Objects.equals(name, courseDTO.name) && Objects.equals(year, courseDTO.year) && Objects.equals(semester, courseDTO.semester);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, specializationDTO, skillsDTO, name, year, semester);
    }
}
