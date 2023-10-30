package com.jayklef.my_blog_apis.service;

import com.jayklef.my_blog_apis.dto.CommentDto;
import com.jayklef.my_blog_apis.entity.Comment;
import org.apache.el.lang.ELArithmetic;

import java.util.List;

public interface CommentService {

    CommentDto createComment(Long postId, CommentDto commentDto);
    List<CommentDto> getAllCommentsByPostId(Long postId);

    CommentDto getCommentById(Long postId, Long commentId);
}
