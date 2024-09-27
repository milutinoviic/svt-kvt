package com.example.sitpass.dto.review;

import com.example.sitpass.dto.comment.CommentCreateDto;
import com.example.sitpass.dto.rate.RateCreateDto;
import com.example.sitpass.model.Comment;
import com.example.sitpass.model.Rate;
import com.example.sitpass.model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewCreateDto {

    @NotNull
    private Integer exerciseCount;
    @NotNull
    private boolean hidden;
    @NotNull
    private Long facilityId;
    @NotNull
    private Long userId;
    @NotNull
    @Valid
    private RateCreateDto rateCreateDto;
    @Valid
    private CommentCreateDto commentCreateDto;
}
