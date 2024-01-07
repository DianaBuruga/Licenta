package com.ulbs.careerstartup.dto;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyDTO that = (CompanyDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(address, that.address) && Objects.equals(website, that.website) && Objects.equals(jobHistoriesDTO, that.jobHistoriesDTO) && Objects.equals(postedJobsDTO, that.postedJobsDTO) && Objects.equals(reviewsDTO, that.reviewsDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, website, jobHistoriesDTO, postedJobsDTO, reviewsDTO);
    }
}
