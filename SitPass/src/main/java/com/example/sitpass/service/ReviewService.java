package com.example.sitpass.service;


import com.example.sitpass.dto.review.ReviewCreateDto;
import com.example.sitpass.dto.review.ReviewDto;
import com.example.sitpass.dto.review.ReviewUpdateDto;
import com.example.sitpass.exceptions.CommentNotFoundException;
import com.example.sitpass.exceptions.FacilityNotFoundException;
import com.example.sitpass.exceptions.RateNotFoundException;
import com.example.sitpass.exceptions.UserNotFoundException;
import com.example.sitpass.model.Review;

import java.util.List;

public interface ReviewService {

    ReviewDto createReview(ReviewCreateDto reviewDto) throws UserNotFoundException, RateNotFoundException, CommentNotFoundException, FacilityNotFoundException;

    List<ReviewDto> getAllReviews();

    Review getReviewById(long id);

    ReviewDto getReviewByIdDto(long id);

    ReviewDto updateReview(Long id, ReviewUpdateDto reviewDto) throws UserNotFoundException;

    ReviewDto convertToDto(Review review);

//    ReviewDto deleteReview(long id);

    List<ReviewDto> getReviewsByFacilityId(Long facilityId);
}
