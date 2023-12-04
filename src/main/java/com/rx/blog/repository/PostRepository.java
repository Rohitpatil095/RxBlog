package com.rx.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rx.blog.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
