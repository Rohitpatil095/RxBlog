package com.rx.blog.payload;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {

	public Date timestamp;
	public String errorMessage;
	public String errorDetails;
}
