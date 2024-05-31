package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.ReviewApiDoc;
import com.ulbs.careerstartup.dto.AverageRating;
import com.ulbs.careerstartup.dto.ReviewDTO;
import com.ulbs.careerstartup.service.ReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

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


    @PostMapping
    public ReviewDTO saveReview(@RequestBody ReviewDTO reviewDTO) {
        return reviewService.saveReview(reviewDTO);
    }

    @PatchMapping
    public ReviewDTO updateReview(@RequestBody ReviewDTO reviewDTO) {
        return reviewService.updateReview(reviewDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable UUID id) {
        reviewService.deleteReview(id);
    }

    @GetMapping("/average-rating/{companyId}")
    public AverageRating getAverageReviewRating(@PathVariable UUID companyId) {
        return reviewService.getAverageReviewRating(companyId);
    }
}
