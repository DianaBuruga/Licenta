package com.ulbs.careerstartup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

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
public class JobHistoryDTO {

    private UUID id;

    @NotNull(message = "Position is required")
    @NotEmpty(message = "Position is required")
    @Size(max = 100, message = INPUT_TOO_LONG)
    private String position;

    @NotNull(message = "Start date is required")
    @NotEmpty(message = "Start date is required")
    private String startDate;

    @NotNull(message = "End date is required")
    @NotEmpty(message = "End date is required")
    private String endDate;

    @NotNull(message = "Description is required")
    @NotEmpty(message = "Description is required")
    private String description;

    @NotNull(message = "Need qualification is required")
    @NotEmpty(message = "Need qualification is required")
    private Boolean needQualification;

    @NotNull(message = "User is required")
    @NotEmpty(message = "User is required")
    @JsonProperty("user")
    private UserDTO userDTO;

    @NotNull(message = "Company is required")
    @NotEmpty(message = "Company is required")
    @JsonProperty("company")
    private CompanyDTO companyDTO;
}
