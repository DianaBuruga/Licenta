package com.ulbs.careerstartup.dto;

import com.ulbs.careerstartup.constant.ExperienceType;
import lombok.*;
import org.hibernate.validator.constraints.URL;

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
public class ExperienceDTO {

    private UUID id;

    @NotNull
    @Size(max = 100, message = INPUT_TOO_LONG)
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Timestamp date;

    @NotNull
    @URL
    private String url;

    @NotNull
    private ExperienceType type;

    @NotNull
    private UserDTO userDTO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExperienceDTO that = (ExperienceDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(date, that.date) && Objects.equals(url, that.url) && type == that.type && Objects.equals(userDTO, that.userDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, date, url, type, userDTO);
    }
}
