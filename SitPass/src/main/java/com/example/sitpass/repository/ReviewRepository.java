package com.example.sitpass.repository;

import com.example.sitpass.model.Review;
import com.example.sitpass.model.WorkDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByFacilityIdAndActiveTrue(long facilityId);




    @Query("SELECT r FROM Review r " +
            "LEFT JOIN FETCH r.user " +
            "LEFT JOIN FETCH r.rate " +
            "LEFT JOIN FETCH r.comment " +
            "LEFT JOIN FETCH r.facility f " +
            "WHERE f.id = :facilityId AND r.active = true AND r.hidden = false")
    List<Review> findReviewsWithAllDetails(@Param("facilityId") Long facilityId);


    @Query("SELECT r FROM Review r WHERE r.user.id = :userId AND r.active = true")
    List<Review> findActiveReviewsByUserId(@Param("userId") Long userId);




}
