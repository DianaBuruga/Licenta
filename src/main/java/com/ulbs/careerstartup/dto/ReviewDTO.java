package com.ulbs.careerstartup.dto;

import com.ulbs.careerstartup.constant.ReviewType;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
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
}
