package com.ulbs.careerstartup.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotNull
    @Size(max=255, message = INPUT_TOO_LONG)
    private String name;

    @NotNull
    private Collection<BibliographyDTO> bibliographiesDTO;

    @NotNull
    private Collection<UserSkillsDTO> userSkillsDTO;

    @NotNull
    private Collection<CourseDTO> coursesDTO;
}
