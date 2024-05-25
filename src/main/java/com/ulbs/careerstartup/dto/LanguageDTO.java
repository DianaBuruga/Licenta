package com.ulbs.careerstartup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ulbs.careerstartup.constant.LanguageLevel;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    private String name;

    @NotNull(message = "Listening level is required")
    private LanguageLevel listening;

    @NotNull(message = "Reading level is required")
    private LanguageLevel reading;

    @NotNull(message = "Speaking level is required")
    private LanguageLevel speaking;

    @NotNull(message = "Conversation level is required")
    private LanguageLevel conversation;

    @NotNull(message = "Writing level is required")
    private LanguageLevel writing;

    @JsonProperty("file")
    private FileDTO fileDTO;

    @NotNull(message = "User is required")
    @JsonProperty("user")
    private UserDTO userDTO;
}
