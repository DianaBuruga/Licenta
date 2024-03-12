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
public class ReferralDTO {

    private UUID id;

    @NotNull
    private String description;

    @NotNull
    private UserDTO teacherDTO;

    @NotNull
    private UserDTO studentDTO;
}
