package com.example.sitpass.dto.review;

import com.example.sitpass.dto.comment.CommentDto;
import com.example.sitpass.dto.facility.FacilityDto;
import com.example.sitpass.dto.rate.RateDto;
import com.example.sitpass.dto.user.UserDto;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReviewDto {
    private Long id;
    private LocalDate createdAt;
    private Integer exerciseCount;
    private boolean hidden;
    private FacilityDto facillty;
    private UserDto user;
    private RateDto rate;
    private CommentDto comment;
}
