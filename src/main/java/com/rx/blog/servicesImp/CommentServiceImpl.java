package com.rx.blog.servicesImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rx.blog.dto.PostDto;
import com.rx.blog.entity.Comment;
import com.rx.blog.entity.Post;
import com.rx.blog.exception.BlogApiException;
import com.rx.blog.exception.ResourceNotdFoundException;
import com.rx.blog.payload.CommentDto;
import com.rx.blog.repository.CommentRepository;
import com.rx.blog.repository.PostRepository;
import com.rx.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private PostServiceImpl postServiceImpl;
	
	@Autowired 
	private PostRepository postRepo;
	
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
		
		PostDto post= postServiceImpl.getPostById(postId);
		
		Comment comm= getCommentByCommentId(commentId);
		
		System.out.println("Comment->" + comm+ " --- post->"+post);
		
		if(post.getId()!=comm.getPost().getId())
		{
			throw new RuntimeException();
		}
		else
		{			
			CommentDto myComments =new CommentDto();
			BeanUtils.copyProperties(comm, myComments);
			return myComments;
		}
		
//		Comment comm= commentRepo.getCommentByCommentIdAndPostId(postId, commentId);
//		System.out.println(comm);
//		if(comm!=null)
//		{
//			CommentDto myComments =new CommentDto();
//			BeanUtils.copyProperties(comm, myComments);
//			return myComments;
//		}
//		else
//		{
//			throw new RuntimeException("some exception happened");
//		}

	}

	@Override
	public Comment getCommentByCommentId(Long commentId) {
		Comment comment= commentRepo.getById(commentId);
		return comment;
	}

	@Override
	public CommentDto updateComment(Long postId, Long commentId, CommentDto commentdto) {
		
		Post post= postRepo.findById(postId).orElseThrow( ()->new ResourceNotdFoundException("Post","id", ""+postId)); 
		
		Comment comment= commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotdFoundException("Comment","id",""+commentId));
		
		if(!comment.getPost().getId().equals(post.getId()))
		{
			new BlogApiException(HttpStatus.BAD_REQUEST,"comment does not belog to post");
		}
		BeanUtils.copyProperties(commentdto, comment);
		Comment updated= commentRepo.save(comment);
		CommentDto updatedComment =new CommentDto();
		BeanUtils.copyProperties(updated, updatedComment);
		return updatedComment;
	}

}
