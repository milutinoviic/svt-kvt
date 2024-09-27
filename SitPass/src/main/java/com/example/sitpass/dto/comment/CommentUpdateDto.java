package com.example.sitpass.dto.comment;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentUpdateDto {

    @NotNull
    private String text;

    private Long commentId;
    private Long userId;
    private Long commentToReplyId;
}


