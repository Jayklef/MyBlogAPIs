package com.jayklef.my_blog_apis.controller;

import com.jayklef.my_blog_apis.dto.PostDto;
import com.jayklef.my_blog_apis.dto.PostResponse;
import com.jayklef.my_blog_apis.service.PostService;
import com.jayklef.my_blog_apis.utils.ParamConstants;
import jakarta.validation.Valid;
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
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        PostDto newPost = postService.createPost(postDto);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = ParamConstants.DEFAULT_PAGE_NO, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = ParamConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = ParamConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = ParamConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir){
        PostResponse posts = postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
        return posts;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable("id") Long id){
        PostDto post = postService.getPost(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@Valid @PathVariable("id") Long id,
                                              @RequestBody PostDto postDto){
        PostDto postToUpdate = postService.updatePost(id, postDto);
        return new ResponseEntity<>(postToUpdate, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }
}
