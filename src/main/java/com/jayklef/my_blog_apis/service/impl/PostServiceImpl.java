package com.jayklef.my_blog_apis.service.impl;

import com.jayklef.my_blog_apis.dto.PostDto;
import com.jayklef.my_blog_apis.entity.Post;
import com.jayklef.my_blog_apis.exception.ResourceNotFoundException;
import com.jayklef.my_blog_apis.repository.PostRepository;
import com.jayklef.my_blog_apis.service.PostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = new Post();
        post.setId(postDto.getId());
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post newPost = postRepository.save(post);

        PostDto postResponse = new PostDto();
        postResponse.setId(newPost.getId());
        postResponse.setTitle(newPost.getTitle());
        postResponse.setDescription(newPost.getDescription());
        postResponse.setContent(newPost.getContent());

        return postResponse;
    }

    @Override
    public List<PostDto> getAllPosts(){
       return postRepository.findAll()
               .stream()
               .map(post -> convertToPostDto(post))
               .collect(Collectors.toList());
    }

    private PostDto convertToPostDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(postDto.getContent());

        return postDto;
    }

    @Override
    public PostDto getPost(Long id){
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return convertToPostDto(post);
    }
}
