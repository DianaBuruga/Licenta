package com.ulbs.careerstartup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Collection;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.ValidationMessages.INPUT_TOO_LONG;
import static com.ulbs.careerstartup.constant.ValidationMessages.INVALID_YEAR_OF_STUDY;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FacultyDTO {

    private UUID id;

    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    @Size(max = 100, message = INPUT_TOO_LONG)
    private String name;

    @NotNull(message = "Address is required")
    @NotEmpty(message = "Address is required")
    @Size(max = 100, message = INPUT_TOO_LONG)
    private String address;

    @NotNull(message = "Years of study are required")
    @NotEmpty(message = "Years of study are required")
    @Max(value = 6, message = INVALID_YEAR_OF_STUDY)
    private Integer yearsOfStudy;

    @JsonProperty("specializations")
    private Collection<SpecializationDTO> specializationsDTO;
}
