package com.ulbs.careerstartup.controller;

import com.ulbs.careerstartup.apidoc.ReviewApiDoc;
import com.ulbs.careerstartup.dto.ReviewDTO;
import com.ulbs.careerstartup.service.ReviewService;
import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Collection<ReviewDTO>> findAllReviews() {
        return ResponseEntity.ok(reviewService.findAllReviews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(reviewService.findById(id));
    }

    @GetMapping(BY_CRITERIA)
    public ResponseEntity<Collection<ReviewDTO>> findReviewsByCriteria(@RequestParam List<SearchCriteria> criteria) {
        return ResponseEntity.ok(reviewService.findReviewsByCriteria(criteria));
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> saveReview(@RequestBody ReviewDTO reviewDTO) {
        return ResponseEntity.ok(reviewService.saveReview(reviewDTO));
    }

    @PatchMapping
    public ResponseEntity<ReviewDTO> updateReview(@RequestBody ReviewDTO reviewDTO) {
        return ResponseEntity.ok(reviewService.updateReview(reviewDTO));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteReview(@RequestBody ReviewDTO reviewDTO) {
        reviewService.deleteReview(reviewDTO);
        return ResponseEntity.noContent().build();
    }
}
