package com.ulbs.careerstartup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "Specialization is required")
    @NotEmpty(message = "Specialization is required")
    @JsonProperty("specialization")
    private SpecializationDTO specializationDTO;

    @NotNull
    @JsonProperty("skills")
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
