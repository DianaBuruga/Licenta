package com.ulbs.careerstartup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
public class BibliographyDTO {

    private UUID id;

    @NotNull(message = "Text is required")
    @NotEmpty(message = "Text is required")
    private String text;

    @NotNull(message = "Title is required")
    @NotEmpty(message = "Title is required")
    @Size(max = 255, message = INPUT_TOO_LONG)
    private String title;

    @NotNull(message = "Date is required")
    @NotEmpty(message = "Date is required")
    private String date;

    @NotNull(message = "Writer is required")
    @NotEmpty(message = "Writer is required")
    @JsonProperty("writer")
    private UserDTO writerDTO;

    @NotNull(message = "Skill is required")
    @NotEmpty(message = "Skill is required")
    @JsonProperty("skill")
    private SkillDTO skillDTO;
}
