package com.mustafa.controller;

import com.mustafa.domain.Comment;
import com.mustafa.dto.request.SaveCommentRequestDto;
import com.mustafa.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.mustafa.constants.RestApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(COMMENT)
public class CommentController {
    private final CommentService commentService;


    @PostMapping(SAVE)
    @CrossOrigin("*")
    public ResponseEntity<Boolean> saveComment(@RequestBody SaveCommentRequestDto dto){
        return ResponseEntity.ok(commentService.saveComment(dto));
    }
}
