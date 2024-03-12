package com.ulbs.careerstartup.dto;

import com.ulbs.careerstartup.constant.ExperienceType;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.ValidationMessages.INPUT_TOO_LONG;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
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

    private FileDTO fileDTO;
}
