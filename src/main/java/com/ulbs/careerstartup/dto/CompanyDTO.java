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

    @NotNull(message = "Job histories are required")
    @NotEmpty(message = "Job histories are required")
    @JsonProperty("jobHistories")
    private Collection<JobHistoryDTO> jobHistoriesDTO;

    @NotNull(message = "Posted jobs are required")
    @NotEmpty(message = "Posted jobs are required")
    @JsonProperty("postedJobs")
    private Collection<PostedJobDTO> postedJobsDTO;

    @NotNull(message = "Reviews are required")
    @NotEmpty(message = "Reviews are required")
    @JsonProperty("reviews")
    private Collection<ReviewDTO> reviewsDTO;
}
