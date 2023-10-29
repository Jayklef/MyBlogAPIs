package com.jayklef.my_blog_apis.service;

import com.jayklef.my_blog_apis.dto.CommentDto;

public interface CommentService {

    CommentDto createComment(Long postId, CommentDto commentDto);
}
