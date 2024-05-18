package com.ulbs.careerstartup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class UserSkillsDTO {

    private UUID userId;

    private UUID skillId;

    @NotNull(message = "User is required")
    @NotEmpty(message = "User is required")
    private UserDTO user;

    @NotNull(message = "Skill is required")
    @NotEmpty(message = "Skill is required")
    @JsonProperty("skill")
    private SkillDTO skillDTO;

    @NotNull(message = "Proficiency is required")
    @NotEmpty(message = "Proficiency is required")
    @JsonProperty("proficiency")
    private Integer proficiencyDTO;
}
