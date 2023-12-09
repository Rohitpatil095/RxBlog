package com.rx.blog.servicesImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rx.blog.dto.PostDto;
import com.rx.blog.entity.Comment;
import com.rx.blog.entity.Post;
import com.rx.blog.payload.CommentDto;
import com.rx.blog.repository.CommentRepository;
import com.rx.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private PostServiceImpl postServiceImpl;
	
	@Override
	public CommentDto createComment(Long postId, CommentDto commentDto) {
		Comment newUserComment= new Comment();
		
		PostDto existingPostDto= postServiceImpl.getPostById(postId);
		
		Post existingPost=new Post();
		BeanUtils.copyProperties(existingPostDto, existingPost);
		newUserComment.setPost(existingPost);
		
		BeanUtils.copyProperties(commentDto, newUserComment);
		commentRepo.save(newUserComment);
//		commentDto.setId();
		return commentDto; 
	}

	@Override
	public List<CommentDto> getAllPostComments(Long postId) {
		List<Comment> allComments= commentRepo.findByPostId(postId);
		
		List<CommentDto> comments= new ArrayList<>();
		CommentDto temp= new CommentDto();
		for(Comment com:allComments)
		{
			BeanUtils.copyProperties(com,temp);
			comments.add(temp);
			System.out.println("temp-> "+temp+ " ---- comments ->"+ comments.toString());
			temp=new CommentDto();
		}
		return comments;
//		return allComments.stream().map(comment -> BeanUtils.copyProperties(comment, allComments));
	}

	@Override
	public List<CommentDto> getAllCommentsByPostId(Long postId) {
		List<Comment> allComments= commentRepo.getAllCommentsByPostId(postId);
		
		List<CommentDto> comments= new ArrayList<>();
		CommentDto temp= new CommentDto();
		for(Comment com:allComments)
		{
			BeanUtils.copyProperties(com,temp);
			comments.add(temp);
			System.out.println("temp-> "+temp+ " ---- comments ->"+ comments.toString());
			temp=new CommentDto();
		}
		return comments;
	}

	@Override
	public CommentDto getCommentByCommentIdAndPostId(Long postId, Long commentId) {
		Comment comm= commentRepo.getCommentByCommentIdAndPostId(postId, commentId);
		CommentDto myComments =new CommentDto();
		BeanUtils.copyProperties(comm, myComments);
		return myComments;
	}

}
