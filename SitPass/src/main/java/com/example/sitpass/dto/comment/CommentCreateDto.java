package com.example.sitpass.dto.comment;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentCreateDto {
    @NotNull
    private String text;
    @NotNull
    private Long userId;
    private Long repliesCommentId;
}
