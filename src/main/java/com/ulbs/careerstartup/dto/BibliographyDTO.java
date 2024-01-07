package com.ulbs.careerstartup.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.ValidationMessages.INPUT_TOO_LONG;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BibliographyDTO {

    private UUID id;

    @NotNull
    private String text;

    @NotNull
    @Size(max = 255, message = INPUT_TOO_LONG)
    private String title;

    @NotNull
    private Timestamp date;

    @NotNull
    private UserDTO writerDTO;

    @NotNull
    private SkillDTO skillDTO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BibliographyDTO that = (BibliographyDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(text, that.text) && Objects.equals(title, that.title) && Objects.equals(date, that.date) && Objects.equals(writerDTO, that.writerDTO) && Objects.equals(skillDTO, that.skillDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, title, date, writerDTO, skillDTO);
    }
}
