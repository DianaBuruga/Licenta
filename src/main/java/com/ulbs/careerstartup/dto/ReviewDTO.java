package com.ulbs.careerstartup.dto;

import com.ulbs.careerstartup.constant.ReviewType;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReviewDTO {

    private UUID id;

    @NotNull
    private String position;

    @NotNull
    private String description;

    @NotNull
    private ReviewType type;

    @NotNull
    private Integer rating;

    @NotNull
    private CompanyDTO companyDTO;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewDTO reviewDTO = (ReviewDTO) o;
        return Objects.equals(id, reviewDTO.id) && Objects.equals(position, reviewDTO.position) && Objects.equals(description, reviewDTO.description) && type == reviewDTO.type && Objects.equals(rating, reviewDTO.rating) && Objects.equals(companyDTO, reviewDTO.companyDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, position, description, type, rating, companyDTO);
    }
}
