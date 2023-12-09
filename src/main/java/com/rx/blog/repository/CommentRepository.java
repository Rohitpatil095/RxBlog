package com.rx.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rx.blog.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	public List<Comment> findByPostId( Long postId);
	
	@Query(name ="",value ="select * from comments where post_id= :postId" ,nativeQuery = true)
	public List<Comment> getAllCommentsByPostId(@Param("postId") Long id);
	
	@Query(value="select * from comments where post_id= :postId and id= :commentId" ,nativeQuery = true)
	public Comment getCommentByCommentIdAndPostId(@Param("postId") Long postId, @Param("commentId") Long commentId);

}
