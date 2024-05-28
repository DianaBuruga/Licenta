package com.ulbs.careerstartup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ulbs.careerstartup.constant.JobStatus;
import com.ulbs.careerstartup.constant.JobType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Collection;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PostedJobDTO {

    private UUID id;

    @NotNull(message = "Title is required")
    @NotEmpty(message = "Title is required")
    private String description;

    @NotNull(message = "Open date is required")
    @NotEmpty(message = "Open date is required")
    private String openUntil;

    @NotNull(message = "Posted date is required")
    @NotEmpty(message = "Posted date is required")
    private String postedDate;

    @NotNull(message = "Status is required")
    @NotEmpty(message = "Status is required")
    private JobStatus status;

    @NotNull(message = "Location is required")
    @NotEmpty(message = "Location is required")
    private String location;

    @NotNull(message = "Type is required")
    @NotEmpty(message = "Type is required")
    private JobType type;

    @NotNull(message = "Job candidates are required")
    @NotEmpty(message = "Job candidates are required")
    @JsonProperty("jobCandidates")
    private Collection<JobCandidatesDTO> jobCandidatesDTO;

    @NotNull(message = "Company is required")
    @NotEmpty(message = "Company is required")
    @JsonProperty("company")
    private CompanyDTO companyDTO;
}
