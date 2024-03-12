package com.ulbs.careerstartup.dto;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
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

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    @URL
    private String website;

    @NotNull
    private Collection<JobHistoryDTO> jobHistoriesDTO;

    @NotNull
    private Collection<PostedJobDTO> postedJobsDTO;

    @NotNull
    private Collection<ReviewDTO> reviewsDTO;
}
