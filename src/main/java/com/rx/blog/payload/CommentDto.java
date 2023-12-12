package com.rx.blog.payload;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
	@Id
	private Long id;
	
	@Size(min=1,message = "Provide Comment body")
	private String body;
	
	@Email(message = "Provided email is not in proper format")
	private String email;
	
	@Size(min = 1, message = "No Proper Name Provided")
	@NotNull
	private String name;
}
