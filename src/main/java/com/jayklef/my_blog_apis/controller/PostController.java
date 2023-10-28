package com.jayklef.my_blog_apis.controller;

import com.jayklef.my_blog_apis.dto.PostDto;
import com.jayklef.my_blog_apis.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        PostDto newPost = postService.createPost(postDto);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> getAllPosts(){
        List<PostDto> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable("id") Long id){
        PostDto post = postService.getPost(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

}
