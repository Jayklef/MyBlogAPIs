package com.jayklef.my_blog_apis.service.impl;

import com.jayklef.my_blog_apis.dto.CommentDto;
import com.jayklef.my_blog_apis.entity.Comment;
import com.jayklef.my_blog_apis.entity.Post;
import com.jayklef.my_blog_apis.exception.ResourceNotFoundException;
import com.jayklef.my_blog_apis.repository.CommentRepository;
import com.jayklef.my_blog_apis.repository.PostRepository;
import com.jayklef.my_blog_apis.service.CommentService;
import org.apache.el.lang.FunctionMapperImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {

        Comment comment = mapToEntity(commentDto);

        // get post by id

        Post post = postRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("Post", "id", postId));

        // set post to comment entity

        comment.setPost(post);

        //save comment to DB

        Comment newComment = commentRepository.save(comment);

        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getAllCommentsByPostId(Long postId) {

        // To use this, explore creating a findCommentsByPostId in post repository
      /*  Post post = postRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("Post", "id", postId));

        List<CommentDto> comments = post.getComments()
                .stream()
                .map(comment -> mapToDto(comment))
                .collect(Collectors.toList());
        return comments; */

        List<Comment> comments = commentRepository.findAllByPostId(postId);
        
        return comments.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());

        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        return comment;
    }
}