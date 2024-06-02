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

    @NotNull(message = "Description is required")
    @NotEmpty(message = "Description is required")
    private String description;

    @NotNull(message = "Description is required")
    @NotEmpty(message = "Description is required")
    private String position;

    @NotNull(message = "Open date is required")
    @NotEmpty(message = "Open date is required")
    private String openUntil;

    private String postedDate;

    private JobStatus status;

    @NotNull(message = "Location is required")
    @NotEmpty(message = "Location is required")
    private String location;

    @NotNull(message = "Type is required")
    private JobType type;

    @JsonProperty("jobCandidates")
    private Collection<JobCandidatesDTO> jobCandidatesDTO;

    @JsonProperty("company")
    private CompanyDTO companyDTO;

    @JsonProperty("user")
    private UserDTO userDTO;

    @JsonProperty("skills")
    private Collection<SkillDTO> skillsDTO;
}
