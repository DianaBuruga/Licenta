package com.ulbs.careerstartup.dto;

import com.ulbs.careerstartup.constant.LanguageLevel;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class LanguageDTO {

    private UUID id;

    @NotNull
    private String name;

    @NotNull
    private LanguageLevel listening;

    @NotNull
    private LanguageLevel reading;

    @NotNull
    private LanguageLevel speaking;

    @NotNull
    private LanguageLevel conversation;

    @NotNull
    private LanguageLevel writing;

    private FileDTO fileDTO;

    @NotNull
    private UserDTO userDTO;
}
