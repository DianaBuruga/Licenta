package com.ulbs.careerstartup.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.ValidationMessages.INPUT_TOO_LONG;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillDTO skillDTO = (SkillDTO) o;
        return Objects.equals(id, skillDTO.id) && Objects.equals(name, skillDTO.name) && Objects.equals(bibliographiesDTO, skillDTO.bibliographiesDTO) && Objects.equals(userSkillsDTO, skillDTO.userSkillsDTO) && Objects.equals(coursesDTO, skillDTO.coursesDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bibliographiesDTO, userSkillsDTO, coursesDTO);
    }
}
