package com.jayklef.my_blog_apis.repository;

import com.jayklef.my_blog_apis.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
