package com.ulbs.careerstartup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.NotNull;
import java.util.Collection;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class CompanyDTO {

    private UUID id;

    @NotNull(message = "Name is required")
    @NotEmpty(message = "Name is required")
    private String name;

    @NotNull(message = "Description is required")
    @NotEmpty(message = "Description is required")
    private String address;

    @NotNull(message = "Website is required")
    @NotEmpty(message = "Website is required")
    @URL(message = "Invalid website")
    private String website;

    private String logoUrl;

    private String description;

    @JsonProperty("jobHistories")
    private Collection<JobHistoryDTO> jobHistoriesDTO;

    @JsonProperty("postedJobs")
    private Collection<PostedJobDTO> postedJobsDTO;

    @JsonProperty("reviews")
    private Collection<ReviewDTO> reviewsDTO;
}
