package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.ReviewApiDoc;
import com.ulbs.careerstartup.dto.ReviewDTO;
import com.ulbs.careerstartup.service.ReviewService;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static com.ulbs.careerstartup.constant.Constants.BY_CRITERIA;

@RestController
@AllArgsConstructor
@RequestMapping("/reviews")
@PreAuthorize("hasAnyAuthority('STUDENT', 'TEACHER', 'COMPANY_REPRESENTATIVE','MODERATOR')")
@Tag(name = "Review", description = "The Review API")
public class ReviewController implements ReviewApiDoc {
    private ReviewService reviewService;

    @GetMapping
    public Collection<ReviewDTO> findAllReviews() {
        return reviewService.findAllReviews();
    }

    @GetMapping("/{id}")
    public ReviewDTO findReviewById(@PathVariable UUID id) {
        return reviewService.findReviewById(id);
    }

    @GetMapping(BY_CRITERIA)
    public Collection<ReviewDTO> findByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return reviewService.findReviewsByCriteria(criteria);
    }

    @PostMapping
    public ReviewDTO saveReview(@RequestBody ReviewDTO reviewDTO) {
        return reviewService.saveReview(reviewDTO);
    }

    @PatchMapping
    public ReviewDTO updateReview(@RequestBody ReviewDTO reviewDTO) {
        return reviewService.updateReview(reviewDTO);
    }

    @DeleteMapping
    public void deleteReview(@RequestBody ReviewDTO reviewDTO) {
        reviewService.deleteReview(reviewDTO);
    }
}
