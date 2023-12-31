package com.rx.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rx.blog.payload.CommentDto;
import com.rx.blog.servicesImp.CommentServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/posts")
public class CommentController {
	
	@Autowired
	private CommentServiceImpl commentServiceImpl;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/{postId}/comment")
	public CommentDto createComment(@PathVariable("postId") Long postId,@Valid @RequestBody CommentDto commentDto)
	{
		return commentServiceImpl.createComment(postId, commentDto);
	}
	
	@GetMapping("/{postId}/comment")
	public List<CommentDto> getAllPostComments(@PathVariable("postId") Long postId)
	{
//		return commentServiceImpl.getAllPostComments(postId);
		return commentServiceImpl.getAllCommentsByPostId(postId);
	}
	
	@GetMapping("/{postId}/comment/{commentId}")
	public CommentDto getCommentByCommentIdAndPostId(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId)
	{
		return commentServiceImpl.getCommentByCommentIdAndPostId(postId, commentId);
	}
	
	@PutMapping("/{postId}/comment/{commentId}")
	public CommentDto updateComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId, @Valid @RequestBody CommentDto commentDto)
	{
		return commentServiceImpl.updateComment(postId, commentId, commentDto);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{postId}/comment/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId)
	{
		commentServiceImpl.deleteComment(postId, commentId);
		return new ResponseEntity<>("Commnet had been deleted",HttpStatus.OK);
	}
}
