package com.ulbs.careerstartup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Collection;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.ValidationMessages.INPUT_TOO_LONG;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class SkillDTO {

    private UUID id;

    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    @Size(max = 255, message = INPUT_TOO_LONG)
    private String name;

    @NotNull(message = "Description is required")
    @NotEmpty(message = "Description is required")
    @JsonProperty("bibliographies")
    private Collection<BibliographyDTO> bibliographiesDTO;

    @NotNull(message = "User skills is required")
    @NotEmpty(message = "User skills is required")
    @JsonProperty("userSkills")
    private Collection<UserSkillsDTO> userSkillsDTO;

    @NotNull(message = "Courses is required")
    @NotEmpty(message = "Courses is required")
    @JsonProperty("courses")
    private Collection<CourseDTO> coursesDTO;
}
