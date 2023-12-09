package com.rx.blog.payload;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class CommentDto {
	@Id
	private Long id;
	private String body;
	private String email;
	private String name;
}
