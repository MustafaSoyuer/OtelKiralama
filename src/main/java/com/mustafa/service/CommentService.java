package com.mustafa.service;

import com.mustafa.domain.Comment;
import com.mustafa.domain.Otel;
import com.mustafa.domain.User;
import com.mustafa.dto.request.SaveCommentRequestDto;
import com.mustafa.exception.ErrorType;
import com.mustafa.exception.UserException;
import com.mustafa.mapper.CommentMapper;
import com.mustafa.repository.CommentRepository;
import com.mustafa.repository.OtelRepository;
import com.mustafa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final OtelRepository otelRepository;

    public Boolean saveComment(SaveCommentRequestDto dto) {
        Optional<User> user = userRepository.findById(dto.getUserId());
        if (user.isEmpty()) {
            throw new UserException(ErrorType.USER_NOT_FOUND);
        }
        Optional<Otel> otel = otelRepository.findById(dto.getHotelId());
        if (otel.isEmpty()) {
            throw new UserException(ErrorType.OTEL_NOT_FOUND);
        }
        Comment comment = CommentMapper.INSTANCE.fromSaveCommentRequestDtoToComment(dto);
        comment.setActive(true);
        comment.setCreateAt(System.currentTimeMillis());
        comment.setUpdateAt(System.currentTimeMillis());
        commentRepository.save(comment);
        return true;

    }
}
