package com.example.demo.model;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private String message;
    private HttpStatus status;

    public ErrorResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
    
    public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}