package com.jayklef.my_blog_apis.repository;

import com.jayklef.my_blog_apis.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
