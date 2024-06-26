package com.ulbs.careerstartup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

import static com.ulbs.careerstartup.constant.ValidationMessages.INPUT_TOO_LONG;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class BibliographyDTO {

    private UUID id;

    @NotNull(message = "Text is required")
    @NotEmpty(message = "Text is required")
    private String text;

    @NotNull(message = "Title is required")
    @NotEmpty(message = "Title is required")
    @Size(max = 255, message = INPUT_TOO_LONG)
    private String title;

    private String date;

    @JsonProperty("writer")
    private UserDTO writerDTO;

    @NotNull(message = "Skill is required")
    @JsonProperty("skill")
    private SkillDTO skillDTO;
}
