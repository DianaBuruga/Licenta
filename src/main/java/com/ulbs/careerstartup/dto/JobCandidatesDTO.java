package com.ulbs.careerstartup.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import jakarta.validation.constraints.NotNull;
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

    private String applicationDate;

    @NotNull(message = "User is required")
    @NotEmpty(message = "User is required")
    @JsonProperty("user")
    private UserDTO userDTO;

    @NotNull(message = "PostedJob is required")
    @NotEmpty(message = "PostedJob is required")
    @JsonProperty("postedJob")
    private PostedJobDTO postedJobDTO;
}
