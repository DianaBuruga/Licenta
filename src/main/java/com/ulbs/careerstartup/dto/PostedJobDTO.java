package com.ulbs.careerstartup.dto;

import com.ulbs.careerstartup.constant.JobStatus;
import com.ulbs.careerstartup.constant.JobType;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostedJobDTO that = (PostedJobDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(openUntil, that.openUntil) && Objects.equals(postedDate, that.postedDate) && status == that.status && Objects.equals(location, that.location) && type == that.type && Objects.equals(jobCandidatesDTO, that.jobCandidatesDTO) && Objects.equals(companyDTO, that.companyDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, openUntil, postedDate, status, location, type, jobCandidatesDTO, companyDTO);
    }
}
