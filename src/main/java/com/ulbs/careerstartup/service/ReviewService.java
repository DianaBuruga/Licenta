package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.dto.AverageRating;
import com.ulbs.careerstartup.dto.ReviewDTO;
import com.ulbs.careerstartup.entity.Company;
import com.ulbs.careerstartup.entity.Review;
import com.ulbs.careerstartup.mapper.Mapper;
import com.ulbs.careerstartup.repository.CompanyRepository;
import com.ulbs.careerstartup.repository.ReviewRepository;
import com.ulbs.careerstartup.specification.GenericSpecification;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class ReviewService {
    private ReviewRepository reviewRepository;
    private CompanyRepository companyRepository;
    private Mapper mapper;

    public Collection<ReviewDTO> findAllReviews() {
        return reviewRepository
                .findAll()
                .stream()
                .map(mapper::reviewToReviewDTO)
                .toList();
    }

    public ReviewDTO findReviewById(UUID id) {
        return reviewRepository.findById(id)
                .map(mapper::reviewToReviewDTO)
                .orElseThrow(() -> new EntityNotFoundException("Review with id " + id + " not found"));
    }

    public ReviewDTO saveReview(ReviewDTO reviewDTO) {
        Review review = mapper.reviewDTOToReview(reviewDTO);
        UUID companyId = reviewDTO.getCompanyDTO().getId();
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company with id " + companyId + " not found"));
        review.setCompany(company);
        return mapper.reviewToReviewDTO(reviewRepository.save(review));
    }

    public ReviewDTO updateReview(ReviewDTO reviewDTO) {
        Review review = reviewRepository.findById(reviewDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Review with id " + reviewDTO.getId() + " not found"));
        review.setRating(reviewDTO.getRating());
        review.setDescription(reviewDTO.getDescription());
        review.setType(reviewDTO.getType());
        review.setRating(reviewDTO.getRating());
        return mapper.reviewToReviewDTO(reviewRepository.save(mapper.reviewDTOToReview(reviewDTO)));
    }

    public void deleteReview(UUID id) {
        reviewRepository.deleteById(id);
    }

    public AverageRating getAverageReviewRating(UUID companyId) {
        return reviewRepository.findAverageRatingAndCountByCompanyId(companyId);
    }

    public Collection<ReviewDTO> findByCriteria(List<SearchCriteria> criteria) {
        return reviewRepository
                .findAll(new GenericSpecification<>(criteria), PageRequest.of(0, 10))
                .map(mapper::reviewToReviewDTO)
                .toList();
    }

}
