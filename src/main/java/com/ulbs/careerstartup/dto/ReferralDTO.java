package com.ulbs.careerstartup.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReferralDTO {

    private UUID id;

    @NotNull
    private String description;

    @NotNull
    private UserDTO teacherDTO;

    @NotNull
    private UserDTO studentDTO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReferralDTO that = (ReferralDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(teacherDTO, that.teacherDTO) && Objects.equals(studentDTO, that.studentDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, teacherDTO, studentDTO);
    }
}
