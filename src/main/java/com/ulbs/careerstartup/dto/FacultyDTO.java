package com.ulbs.careerstartup.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.ValidationMessages.INPUT_TOO_LONG;
import static com.ulbs.careerstartup.constant.ValidationMessages.INVALID_YEAR_OF_STUDY;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class FacultyDTO {

    private UUID id;

    @NotNull
    @Size(max = 100, message = INPUT_TOO_LONG)
    private String name;

    @NotNull
    @Size(max = 100, message = INPUT_TOO_LONG)
    private String address;

    @NotNull
    @Max(value = 6, message = INVALID_YEAR_OF_STUDY)
    private Integer yearsOfStudy;

    private Collection<SpecializationDTO> specializationsDTO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacultyDTO that = (FacultyDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(yearsOfStudy, that.yearsOfStudy) && Objects.equals(specializationsDTO, that.specializationsDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, yearsOfStudy, specializationsDTO);
    }
}
