package com.example.sitpass.dto.review;


import com.example.sitpass.dto.comment.CommentUpdateDto;
import com.example.sitpass.dto.rate.RateUpdateDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReviewUpdateDto {
    @NotNull
    private Integer exerciseCount;
    @NotNull
    private boolean hidden;
    @NotNull
    private RateUpdateDto rateUpdateDto;
    private CommentUpdateDto commentUpdateDto;

}
