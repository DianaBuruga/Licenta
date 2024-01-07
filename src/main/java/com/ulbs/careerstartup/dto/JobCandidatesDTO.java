package com.ulbs.careerstartup.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class JobCandidatesDTO {

    private UUID candidateId;

    private UUID jobId;

    private Timestamp applicationDate;

    @NotNull
    private UserDTO userDTO;

    @NotNull
    private PostedJobDTO postedJobDTO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobCandidatesDTO that = (JobCandidatesDTO) o;
        return Objects.equals(candidateId, that.candidateId) && Objects.equals(jobId, that.jobId) && Objects.equals(applicationDate, that.applicationDate) && Objects.equals(userDTO, that.userDTO) && Objects.equals(postedJobDTO, that.postedJobDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(candidateId, jobId, applicationDate, userDTO, postedJobDTO);
    }
}
