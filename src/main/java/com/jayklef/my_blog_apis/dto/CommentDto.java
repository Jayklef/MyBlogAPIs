package com.jayklef.my_blog_apis.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;

    @NotEmpty(message = "Please enter your name")
    private String name;

    @Email(message = "Please provide a valid email")
    private String email;

    @NotEmpty(message = "comment cannot not be empty")
    @Size(message = "Comment must contain a minimum of 10 characters")
    private String body;
}
