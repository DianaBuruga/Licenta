package com.ulbs.careerstartup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ulbs.careerstartup.constant.ReviewType;
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
public class ReviewDTO {

    private UUID id;

    @NotNull(message = "Position is required")
    @NotEmpty(message = "Position is required")
    private String position;

    @NotNull(message = "Description is required")
    @NotEmpty(message = "Description is required")
    private String description;

    @NotNull(message = "Type is required")
    @NotEmpty(message = "Type is required")
    private ReviewType type;

    @NotNull(message = "Rating is required")
    @NotEmpty(message = "Rating is required")
    private Integer rating;

    @NotNull(message = "Company is required")
    @NotEmpty(message = "Company is required")
    @JsonProperty("company")
    private CompanyDTO companyDTO;
}
