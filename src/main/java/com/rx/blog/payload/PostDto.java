package com.rx.blog.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostDto {
	
	private Long id;
	
	@Size(min=1,message = "Provide title for post")
	private String title;
	
	@NotNull
	private String description;
	@NotNull
	private String content;

}
