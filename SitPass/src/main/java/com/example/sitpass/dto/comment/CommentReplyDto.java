package com.example.sitpass.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentReplyDto {
    @NotNull
    Long commentId;
    @NotNull
    Long userId;
    @NotNull
    @NotBlank
    String content;
}
