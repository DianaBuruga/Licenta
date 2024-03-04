/*
package com.ulbs.careerstartup.mapper;

import com.ulbs.careerstartup.dto.ReviewDTO;
import com.ulbs.careerstartup.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = CompanyMapper.class)
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mapping(source = "company", target = "companyDTO")
    ReviewDTO reviewToReviewDTO(Review review);

    @Mapping(source = "companyDTO", target = "company")
    Review reviewDTOToReview(ReviewDTO reviewDTO);
}*/
