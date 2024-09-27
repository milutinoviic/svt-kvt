package com.example.sitpass.service.implementation;

import com.example.sitpass.dto.comment.CommentCreateDto;
import com.example.sitpass.dto.comment.CommentDto;
import com.example.sitpass.dto.comment.CommentUpdateDto;
import com.example.sitpass.dto.facility.FacilityDto;
import com.example.sitpass.dto.rate.RateDto;
import com.example.sitpass.dto.rate.RateUpdateDto;
import com.example.sitpass.dto.review.ReviewCreateDto;
import com.example.sitpass.dto.review.ReviewDto;
import com.example.sitpass.dto.review.ReviewUpdateDto;
import com.example.sitpass.dto.user.UserDto;
import com.example.sitpass.exceptions.CommentNotFoundException;
import com.example.sitpass.exceptions.FacilityNotFoundException;
import com.example.sitpass.exceptions.RateNotFoundException;
import com.example.sitpass.exceptions.UserNotFoundException;
import com.example.sitpass.model.*;
import com.example.sitpass.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService implements com.example.sitpass.service.ReviewService {
    private final UserService userService;
    private final RateServiceImpl rateService;
    private final CommentServiceImpl commentService;
    private final ReviewRepository reviewRepository;
    private final FacilityService facilityService;


    @Autowired
    public ReviewService(UserService userService, RateServiceImpl rateService, CommentServiceImpl commentService, ReviewRepository reviewRepository, FacilityService facilityService) {
        this.userService = userService;
        this.rateService = rateService;
        this.commentService = commentService;
        this.reviewRepository = reviewRepository;
        this.facilityService = facilityService;
    }

    @Override
    public ReviewDto createReview(ReviewCreateDto reviewCreateDto) throws UserNotFoundException, RateNotFoundException, CommentNotFoundException, FacilityNotFoundException {
        Review review = new Review();

        review.setCreatedAt(LocalDate.now());
        review.setExerciseCount(reviewCreateDto.getExerciseCount());
        review.setActive(true);
        Facility facility= facilityService.getFacilityById(reviewCreateDto.getFacilityId());
        if (facility == null) {
            throw new FacilityNotFoundException("FACILITY NOT FOUND");
        }
        review.setFacility(facility);
        User user = userService.getUserById(reviewCreateDto.getUserId());
        if(user == null){
            throw new UserNotFoundException("Not found user");
        }
        review.setUser(user);
        Rate rate = rateService.createRate(reviewCreateDto.getRateCreateDto());
        if(rate == null){
            throw new RateNotFoundException("Not found user");
        }
        review.setRate(rate);
        CommentCreateDto commentCreateDto = reviewCreateDto.getCommentCreateDto();
        if(commentCreateDto != null){
            Comment comm = commentService.createComment(reviewCreateDto.getCommentCreateDto());
            review.setComment(comm);
        }

        reviewRepository.save(review);

        return convertToDto(review);
    }
    @Override
    public List<ReviewDto> getAllReviews() {
        List<ReviewDto> reviewDtos = new ArrayList<>();
        for (Review review : reviewRepository.findAll()) {
            ReviewDto reviewDto = convertToDto(review);
            reviewDtos.add(reviewDto);
        }
        return reviewDtos;
    }
    @Override
    public ReviewDto convertToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setCreatedAt(review.getCreatedAt());
        reviewDto.setExerciseCount(review.getExerciseCount());//load from backend
        reviewDto.setHidden(review.isHidden());
        FacilityDto facilityDto = facilityService.getFacilityByIdDto(review.getFacility().getId());
        reviewDto.setFacillty(facilityDto);
        UserDto userDto = userService.getUserByIdDto(review.getUser().getId());
        reviewDto.setUser(userDto);
        RateDto rate = rateService.getRateByIdDto(review.getRate().getId());
        reviewDto.setRate(rate);

        if(review.getComment() != null){
            CommentDto commentDto = commentService.getCommentByIdDto(review.getComment().getId());
            reviewDto.setComment(commentDto);
        }

        return reviewDto;
    }
    @Override
    public Review getReviewById(long id) {
        Review review = reviewRepository.findById(id).orElse(null);
        if(review == null){
            return null;
        }

        return review;
    }
    @Override
    public ReviewDto getReviewByIdDto(long id) {
        for (Review review : reviewRepository.findAll()) {
            if(review.getId() == id) {
                ReviewDto reviewDto = convertToDto(review);
                return reviewDto;
            }
        }
        return null;
    }
    @Override
    public ReviewDto updateReview(Long id, ReviewUpdateDto reviewUpdateDto) throws UserNotFoundException {
        Review review = reviewRepository.findById(id).orElse(null);
        if(review == null) {
            return null;
        }
        review.setExerciseCount(reviewUpdateDto.getExerciseCount());

        RateUpdateDto rateUpdateDto = reviewUpdateDto.getRateUpdateDto();
        Rate rate = rateService.getRateById(rateUpdateDto.getRateId());
        rate.setSpace(rateUpdateDto.getSpace());
        rate.setStaff(rateUpdateDto.getStaff());
        rate.setHygiene(rateUpdateDto.getHygiene());
        rate.setEquipment(rateUpdateDto.getEquipment());

        review.setRate(rate);

        CommentUpdateDto commentUpdateDto = reviewUpdateDto.getCommentUpdateDto();
        Comment comment;
        if(commentUpdateDto.getCommentId() == null){
            comment = commentService.createComment(commentUpdateDto);
        }else{
            comment = commentService.getCommentById(commentUpdateDto.getCommentId());
            if(comment == null){
                return null;
            }
            comment.setText(commentUpdateDto.getText());
        }

        review.setComment(comment);

        reviewRepository.save(review);

        return convertToDto(review);
    }
//    @Override
//    public ReviewDto deleteReview(long id) {
//        Review review = reviewRepository.findById(id).orElse(null);
//        if(review == null) {
//            return null;
//        }
//        reviewRepository.deleteById(id);
//        ReviewDto reviewDto = convertToDto(review);
//        return reviewDto;
//
//
//    }

    @Override
    public List<ReviewDto> getReviewsByFacilityId(Long facilityId) {
        List<Review> reviews = reviewRepository.findReviewsWithAllDetails(facilityId);
        List<ReviewDto> reviewDtos = new ArrayList<>();
        for (Review review : reviews) {
            ReviewDto reviewDto = convertToDto(review);
            reviewDtos.add(reviewDto);
        }
        return reviewDtos;
    }

    public void hideReview(Long id) {
        Review review = reviewRepository.findById(id).orElse(null);
        if (review != null) {
            review.setHidden(true);
            reviewRepository.save(review);
        }
    }


    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id).orElse(null);
        if (review != null) {
            review.setActive(false);
            reviewRepository.save(review);
        }
    }

    public List<ReviewDto> getActiveReviewsByUserId(Long userId) {
        List<Review> reviews = reviewRepository.findActiveReviewsByUserId(userId);
        List<ReviewDto> reviewDtos = new ArrayList<>();
        for (Review review : reviews) {
            ReviewDto reviewDto = convertToDto(review);
            reviewDtos.add(reviewDto);
        }
        return reviewDtos;
    }


}


