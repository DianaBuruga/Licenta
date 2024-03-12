package com.ulbs.careerstartup.dto;

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
public class UserSkillsDTO {

    private UUID userId;

    private UUID skillId;

    @NotNull
    private UserDTO user;

    @NotNull
    private SkillDTO skillDTO;

    @NotNull
    private Integer proficiencyDTO;
}
