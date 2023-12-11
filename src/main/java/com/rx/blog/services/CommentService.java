package com.rx.blog.services;

import java.util.List;

import com.rx.blog.entity.Comment;
import com.rx.blog.payload.CommentDto;

public interface CommentService {
	
	public CommentDto createComment(Long postId, CommentDto commentDto);
	public List<CommentDto> getAllPostComments(Long postId);
	public List<CommentDto> getAllCommentsByPostId(Long postId);
	public CommentDto getCommentByCommentIdAndPostId(Long postId, Long commentId);
	public Comment getCommentByCommentId(Long commentId);
	public CommentDto updateComment(Long postId, Long commentId, CommentDto commentdto);
	public void deleteComment(Long postId, Long commentId);
}
