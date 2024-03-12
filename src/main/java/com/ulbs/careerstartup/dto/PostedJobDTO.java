package com.ulbs.careerstartup.dto;

import com.ulbs.careerstartup.constant.JobStatus;
import com.ulbs.careerstartup.constant.JobType;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
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

    @NotNull
    private String description;

    @NotNull
    private Timestamp openUntil;

    @NotNull
    private Timestamp postedDate;

    @NotNull
    private JobStatus status;

    @NotNull
    private String location;

    @NotNull
    private JobType type;

    @NotNull
    private Collection<JobCandidatesDTO> jobCandidatesDTO;

    @NotNull
    private CompanyDTO companyDTO;
}
