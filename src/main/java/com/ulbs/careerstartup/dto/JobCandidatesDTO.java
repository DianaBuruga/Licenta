package com.ulbs.careerstartup.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class JobCandidatesDTO {

    private UUID candidateId;

    private UUID jobId;

    private Timestamp applicationDate;

    @NotNull
    private UserDTO userDTO;

    @NotNull
    private PostedJobDTO postedJobDTO;
}
