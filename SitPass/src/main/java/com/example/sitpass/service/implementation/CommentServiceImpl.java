package com.example.sitpass.service.implementation;

import com.example.sitpass.dto.comment.CommentCreateDto;
import com.example.sitpass.dto.comment.CommentReplyDto;
import com.example.sitpass.dto.comment.CommentUpdateDto;
import com.example.sitpass.dto.comment.CommentDto;
import com.example.sitpass.dto.user.UserDto;
import com.example.sitpass.exceptions.CommentNotFoundException;
import com.example.sitpass.exceptions.UserNotFoundException;
import com.example.sitpass.model.Comment;
import com.example.sitpass.model.User;
import com.example.sitpass.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements com.example.sitpass.service.CommentService {

    private final UserService userService;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(UserService userService, CommentRepository commentRepository) {
        this.userService = userService;
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment createComment(CommentCreateDto commentCreateDto) throws  UserNotFoundException{
        Comment comment = new Comment();
        comment.setText(commentCreateDto.getText());
        comment.setCreatedAt(LocalDateTime.now());
        User user = userService.getUserById(commentCreateDto.getUserId());
        if(user == null){
            throw new UserNotFoundException("User nije pronadjen");
        }

        comment.setUser(user);
        commentRepository.save(comment);
        return comment;

    }

    @Override
    public Comment createComment(CommentUpdateDto commentUpdateDto) throws UserNotFoundException {
        Comment comment = new Comment();
        comment.setText(commentUpdateDto.getText());
        comment.setCreatedAt(LocalDateTime.now());
        User user = userService.getUserById(commentUpdateDto.getUserId());
        if(user == null){
            throw new UserNotFoundException("User nije pronadjen");
        }

        comment.setUser(user);
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public Comment convertDtoToComment(CommentUpdateDto commentDto) {
        Comment comment = new Comment();
        comment.setText(commentDto.getText());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUser(userService.getUserById(commentDto.getUserId()));

        return comment;


    }

    @Override
    public List<CommentDto> getAllCommentsDto() {
        List<CommentDto> commentDtos = new ArrayList<>();
        for (Comment comment : commentRepository.findAll()) {
            CommentDto commentDto = convertionToDto(comment);
            commentDtos.add(commentDto);
        }
        return commentDtos;
    }



    @Override
    public Comment getCommentById(long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if(comment == null){
            return null;
        }
        return comment;
    }

    @Override
    public CommentDto getCommentByIdDto(long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if(comment == null){
            return null;
        }
        CommentDto commentDto = convertionToDto(comment);
        return commentDto;
    }

    @Override
    public CommentDto convertionToDto(Comment comment) {
         CommentDto commentDto = new CommentDto();
         commentDto.setId(comment.getId());
         commentDto.setText(comment.getText());
         commentDto.setCreatedAl(comment.getCreatedAt());
         UserDto user = userService.getUserByIdDto(comment.getUser().getId());
         commentDto.setUser(user);

         Comment replyComment = commentRepository.findFirstByRepliesToCommentId(comment.getId()).orElse(null);

         if (replyComment != null) {
             CommentDto replyCommentDto = convertionToDto(replyComment);
             commentDto.setReplyComment(replyCommentDto);
         }

         return commentDto;

    }

    @Override
    public CommentDto updateComment(Long id, CommentUpdateDto commentDetails) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment == null) {
            return null;
        }
        comment.setText(commentDetails.getText());
        commentRepository.save(comment);
        CommentDto commentDto = convertionToDto(comment);
        return commentDto;
    }

    @Override
    public CommentDto deleteComment(long id) {
        Comment comment = commentRepository.findById(id).orElse(null);
        if (comment == null) {
            return null;
        }
        commentRepository.delete(comment);
        CommentDto commentDto = convertionToDto(comment);
        return commentDto;
    }

    @Override
    public CommentDto replyToComment(CommentReplyDto commentReplyDto) throws UserNotFoundException, CommentNotFoundException {
        User user = userService.getUserById(commentReplyDto.getUserId());

        if (user == null) {
            throw new UserNotFoundException("User nije pronadjen");
        }

        Comment commentToReply = commentRepository.findById(commentReplyDto.getCommentId()).orElse(null);

        if (commentToReply == null) {
            throw new CommentNotFoundException("Komentar nije pronadjen");
        }

        Comment comment = new Comment();
        comment.setRepliesToComment(commentToReply);
        comment.setUser(user);
        comment.setText(commentReplyDto.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        return convertionToDto(commentRepository.save(comment));
    }
}
