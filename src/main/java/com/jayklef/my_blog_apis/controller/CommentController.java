package com.jayklef.my_blog_apis.controller;

import com.jayklef.my_blog_apis.dto.CommentDto;
import com.jayklef.my_blog_apis.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable("postId") Long postId,
                                                    @RequestBody CommentDto commentDto){
        CommentDto newComment = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }
}
