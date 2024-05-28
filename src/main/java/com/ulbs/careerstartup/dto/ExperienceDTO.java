package com.ulbs.careerstartup.dto;

import com.ulbs.careerstartup.constant.ExperienceType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;

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

    @NotNull(message = "Title is required")
    @Size(max = 100, message = INPUT_TOO_LONG)
    private String title;

    @NotNull(message = "Description is required")
    private String description;

    @NotNull(message = "Date is required")
    private String date;

    @URL(message = "Invalid URL")
    private String url;

    @NotNull(message = "ExperienceType is required")
    private ExperienceType type;

    private UserDTO userDTO;

    private FileDTO fileDTO;
}
