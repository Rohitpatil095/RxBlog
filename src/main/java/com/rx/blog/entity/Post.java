package com.rx.blog.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "posts", uniqueConstraints = {
		@UniqueConstraint(columnNames= {"title"})
		}	
	  )
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="title",nullable = false)
	private String title;
	
	@Column(name="description",nullable = false)
	private String description;
	
	@Column(name="content",nullable = false)
	private String content;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy= "post", orphanRemoval = true)
	Set<Comment> comments= new HashSet<>();

}
