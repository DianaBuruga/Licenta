package com.ulbs.careerstartup.service;

import com.ulbs.careerstartup.dto.ReviewDTO;
import com.ulbs.careerstartup.mapper.Mapper;
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
    private Mapper mapper;

    public Collection<ReviewDTO> findAllReviews() {
        return reviewRepository
                .findAll()
                .stream()
                .map(mapper::reviewToReviewDTO)
                .toList();
    }

    public ReviewDTO findById(UUID id) {
        return reviewRepository.findById(id)
                .map(mapper::reviewToReviewDTO)
                .orElseThrow(() -> new EntityNotFoundException("Review with id " + id + " not found"));
    }

    public ReviewDTO saveReview(ReviewDTO reviewDTO) {
        return mapper.reviewToReviewDTO(reviewRepository.save(mapper.reviewDTOToReview(reviewDTO)));
    }

    public ReviewDTO updateReview(ReviewDTO reviewDTO) {
        return mapper.reviewToReviewDTO(reviewRepository.save(mapper.reviewDTOToReview(reviewDTO)));
    }

    public void deleteReview(ReviewDTO reviewDTO) {
        reviewRepository.delete(mapper.reviewDTOToReview(reviewDTO));
    }

    public Collection<ReviewDTO> findReviewsByCriteria(List<SearchCriteria> criteria) {
        return reviewRepository
                .findAll(new GenericSpecification<>(criteria), PageRequest.of(0, 10))
                .map(mapper::reviewToReviewDTO)
                .toList();
    }
}
