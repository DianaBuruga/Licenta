package com.ulbs.careerstartup.repository;

import com.ulbs.careerstartup.dto.AverageRating;
import com.ulbs.careerstartup.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID>, JpaSpecificationExecutor<Review> {

    @Query("SELECT new com.ulbs.careerstartup.dto.AverageRating(AVG(r.rating), COUNT(r)) FROM Review r WHERE r.company.id = :companyId")
    AverageRating findAverageRatingAndCountByCompanyId(@Param("companyId") UUID companyId);
}