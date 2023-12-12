package com.rx.blog.servicesImp;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rx.blog.entity.Post;
import com.rx.blog.exception.ResourceNotdFoundException;
import com.rx.blog.payload.PostDto;
import com.rx.blog.repository.PostRepository;
import com.rx.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository pRepo;
	
	@Override
	public PostDto savePost(PostDto postDto) {
		Post newPost= new Post();
		BeanUtils.copyProperties(postDto,newPost);
		
		Post savedPost =pRepo.save(newPost);
		
		postDto.setId(savedPost.getId());
		return postDto;
	}

//	@Override
//	public List<Post> getAllPosts(int pageSize,int pageNo, Pageable page) {
//		List<Post> allPosts=new ArrayList<>();
//		
//		allPosts.addAll(pRepo.findAll());
//		if(allPosts.size()<1)
//		{
//			return null;
//		}
//		return allPosts;
//		
//	}

	@Override
	public List<Post> getAllPosts() {
		List<Post> allPosts=new ArrayList<>();
		
		allPosts.addAll(pRepo.findAll());
		if(allPosts.size()<1)
		{
			return null;
		}
		return allPosts;
		
	}
	
	@Override
	public PostDto getPostById(Long id) {
		Post post=new Post();
		Post retrivedPost=pRepo.findById(id).orElseThrow(()-> new ResourceNotdFoundException("Post","Id",""+id));
//		if(retrivedPost.isPresent())
//		{
//			post=retrivedPost.get();
//		}
//		else
//		{
//			return null;
//		}
		PostDto myRetrivedPost= new PostDto();
		BeanUtils.copyProperties(post, myRetrivedPost);
		return myRetrivedPost;
	}

	@Override
	public PostDto updateUserPost(Long id, PostDto postDto) {
		PostDto existingPost= new PostDto();
		existingPost= getPostById(id);
		
		if(existingPost ==null)
		{
			return null;
		}
		
		existingPost.setTitle(postDto.getTitle() !=null ?  postDto.getTitle() : existingPost.getTitle());
		existingPost.setContent(postDto.getContent() !=null ? postDto.getContent(): existingPost.getContent());
		existingPost.setDescription(postDto.getDescription() !=null ? postDto.getDescription() : existingPost.getDescription());
		
		return savePost(existingPost);
	}

	@Override
	public PostDto deletePostById(Long id) {
		
		PostDto existingPost=getPostById(id);
		if(existingPost==null)
		{
			return null;
		}
		pRepo.deleteById(id);
		return existingPost;
	}
	
	

}
