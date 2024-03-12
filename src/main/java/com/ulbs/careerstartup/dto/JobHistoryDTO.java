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
public class JobHistoryDTO {

    private UUID id;

    @NotNull
    @Size(max = 100, message = INPUT_TOO_LONG)
    private String position;

    @NotNull
    private Timestamp startDate;

    @NotNull
    private Timestamp endDate;

    @NotNull
    private String description;

    @NotNull
    private Boolean needQualification;

    @NotNull
    private UserDTO userDTO;

    @NotNull
    private CompanyDTO companyDTO;
}
