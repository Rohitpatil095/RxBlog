package com.rx.blog.services;

import java.awt.print.Pageable;
import java.util.List;

import com.rx.blog.entity.Post;
import com.rx.blog.payload.PostDto;

public interface PostService {

	public PostDto savePost(PostDto postDto);
//	public List<Post> getAllPosts(int pageSize,int pageNo,Pageable page);
	public List<Post> getAllPosts();
	public PostDto getPostById(Long id);
	public PostDto updateUserPost(Long id, PostDto postDto);
	public PostDto deletePostById(Long id);
}
