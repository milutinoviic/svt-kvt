package com.example.sitpass.dto.comment;

import com.example.sitpass.dto.user.UserDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentDto {
    private Long id;
    private String text;
    private LocalDateTime createdAl;
    private UserDto user;
    private CommentDto replyComment;
}
