package com.example.sitpass.controller;

import com.example.sitpass.dto.review.ReviewCreateDto;
import com.example.sitpass.dto.review.ReviewDto;
import com.example.sitpass.dto.review.ReviewUpdateDto;
import com.example.sitpass.exceptions.CommentNotFoundException;
import com.example.sitpass.exceptions.FacilityNotFoundException;
import com.example.sitpass.exceptions.RateNotFoundException;
import com.example.sitpass.exceptions.UserNotFoundException;
import com.example.sitpass.service.implementation.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        List<ReviewDto> reviewsDto = reviewService.getAllReviews();
        return new ResponseEntity<>(reviewsDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable long id) {
        ReviewDto reviewDto = reviewService.getReviewByIdDto(id);
        if (reviewDto != null) {
            return new ResponseEntity<>(reviewDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@Valid @RequestBody ReviewCreateDto review) {
        try {
            ReviewDto createdReviewDto = reviewService.createReview(review);
            return new ResponseEntity<>(createdReviewDto, HttpStatus.CREATED);
        }catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (RateNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }catch (CommentNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        catch (FacilityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/hide/{id}")
    public ResponseEntity<Void> hideReview(@PathVariable Long id) {
        reviewService.hideReview(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable Long id, @RequestBody ReviewUpdateDto review) throws UserNotFoundException {
        try{
            return new ResponseEntity<>(reviewService.updateReview(id, review), HttpStatus.OK);
        }catch(UserNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<ReviewDto> deleteReview(@PathVariable long id) {
//        ReviewDto deletedReviewDto = reviewService.deleteReview(id);
//        if (deletedReviewDto != null) {
//            return new ResponseEntity<>(deletedReviewDto, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
    @GetMapping("/facility/{id}")
    public ResponseEntity<List<ReviewDto>> getReviewsByFacilityId(@PathVariable Long id){
        List<ReviewDto> reviewsDto = reviewService.getReviewsByFacilityId(id);
        if (reviewsDto != null) {
            return new ResponseEntity<>(reviewsDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewDto>> getActiveReviewsByUserId(@PathVariable Long userId) {
        List<ReviewDto> reviewsDto = reviewService.getActiveReviewsByUserId(userId);
        if (reviewsDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(reviewsDto, HttpStatus.OK);
    }

}
