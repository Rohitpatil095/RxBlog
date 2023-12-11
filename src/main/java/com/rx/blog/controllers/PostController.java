package com.rx.blog.controllers;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rx.blog.dto.PostDto;
import com.rx.blog.entity.Post;
import com.rx.blog.services.PostService;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
	
	@Autowired
	PostService postService;

	@PostMapping
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto)
	{
		return new ResponseEntity<PostDto>(postService.savePost(postDto),HttpStatus.CREATED);
	}
	
//	@GetMapping
//	public List<Post> getAllUserPostsPageable(@RequestParam(defaultValue = "10",name="pageSize", required = false) int pageSize,
//			@RequestParam(defaultValue = "0", name = "pageNo", required = false) int pageNo, Pageable page){
//		return postService.getAllPosts(pageSize,pageNo,page);
//	}
	
	@GetMapping
	public List<Post> getAllUserPosts(){
		return postService.getAllPosts();
	}
	
	@GetMapping("{id}")
	public PostDto getUserPostById(@PathVariable("id") Long id)
	{
		return postService.getPostById(id);
	}
	
	@PutMapping("{id}")
	public PostDto updateExistingPost(@PathVariable("id") Long id, @RequestBody PostDto postDto) {
		
		return postService.updateUserPost(id, postDto);
		
	}
	
	@DeleteMapping("{id}")
	public PostDto deleteUserPostById(@PathVariable("id") Long id)
	{
		return postService.deletePostById(id);
	}
	
}
