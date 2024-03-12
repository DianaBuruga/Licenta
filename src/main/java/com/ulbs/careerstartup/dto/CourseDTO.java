package com.ulbs.careerstartup.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.ValidationMessages.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
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
}
