package com.example.sitpass.service;


import com.example.sitpass.dto.comment.CommentCreateDto;
import com.example.sitpass.dto.comment.CommentReplyDto;
import com.example.sitpass.dto.comment.CommentUpdateDto;
import com.example.sitpass.dto.comment.CommentDto;
import com.example.sitpass.exceptions.CommentNotFoundException;
import com.example.sitpass.exceptions.UserNotFoundException;
import com.example.sitpass.model.Comment;


import java.util.List;

public interface CommentService {

    Comment createComment(CommentCreateDto commentDto) throws UserNotFoundException;

    Comment createComment(CommentUpdateDto commentDto) throws UserNotFoundException;
    Comment convertDtoToComment(CommentUpdateDto commentDto);

    List<CommentDto> getAllCommentsDto();

    Comment getCommentById(long id);

    CommentDto getCommentByIdDto(long id);

    CommentDto convertionToDto(Comment comment);

    CommentDto updateComment(Long id, CommentUpdateDto commentDetails);

    CommentDto deleteComment(long id);

    CommentDto replyToComment(CommentReplyDto commentReplyDto) throws UserNotFoundException, CommentNotFoundException;
}
