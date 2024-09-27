package com.example.sitpass.controller;

import com.example.sitpass.dto.comment.CommentReplyDto;
import com.example.sitpass.dto.comment.CommentUpdateDto;
import com.example.sitpass.dto.comment.CommentDto;
import com.example.sitpass.exceptions.CommentNotFoundException;
import com.example.sitpass.exceptions.UserNotFoundException;
import com.example.sitpass.model.Comment;
import com.example.sitpass.service.implementation.CommentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentServiceImpl commentService;

    @Autowired
    public CommentController(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments() {
        List<CommentDto> commentsDto = commentService.getAllCommentsDto();
        return new ResponseEntity<>(commentsDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable long id) {
        CommentDto commentDto = commentService.getCommentByIdDto(id);
        if (commentDto != null) {
            return new ResponseEntity<>(commentDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/replyComment")
    public ResponseEntity<CommentDto> createComment(@Valid @RequestBody CommentReplyDto comment) {

        try {
            CommentDto createdCommentDto = commentService.replyToComment(comment);
            return new ResponseEntity<>(createdCommentDto, HttpStatus.CREATED);
        } catch (UserNotFoundException | CommentNotFoundException u) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long id,@Valid @RequestBody CommentUpdateDto comment) {
        CommentDto updatedCommentDto = commentService.updateComment(id, comment);
        return new ResponseEntity<>(updatedCommentDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommentDto> deleteComment(@PathVariable long id) {
        CommentDto deletedCommentDto = commentService.deleteComment(id);
        if (deletedCommentDto != null) {
            return new ResponseEntity<>(deletedCommentDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
