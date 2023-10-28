package com.jayklef.my_blog_apis.service;

import com.jayklef.my_blog_apis.dto.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();
    PostDto getPost(Long id);

    PostDto updatePost(Long id, PostDto postDto);

    void deletePost(Long id);
}
