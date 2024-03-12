package com.ulbs.careerstartup.dto;

import lombok.*;

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
}
