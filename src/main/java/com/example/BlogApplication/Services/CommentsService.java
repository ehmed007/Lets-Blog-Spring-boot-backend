package com.example.BlogApplication.Services;

import com.example.BlogApplication.DTOs.Request.CommentRequest;
import com.example.BlogApplication.DTOs.Response.CommentResponse;
import com.example.BlogApplication.Entities.Comments;
import com.example.BlogApplication.Exceptions.ResourceNotFoundException;

import java.util.List;

public interface CommentsService {
    public Comments addComment(CommentRequest obj) throws ResourceNotFoundException;
    public Comments addComment(Comments obj);

    public Comments updateComment(CommentRequest obj, Integer commentId) throws ResourceNotFoundException;

    public Comments updateComment(Comments obj, Integer commentId) throws ResourceNotFoundException;

    public void deleteComment(Integer commentId) throws ResourceNotFoundException;

    public Comments getOneComment(Integer commentId) throws ResourceNotFoundException;

    public List<Comments> getAllComment();

    public CommentRequest commentToRequest(Comments obj);

    public Comments requestToComment(CommentRequest obj) throws ResourceNotFoundException;

    public CommentResponse commentToResponse(Comments obj);
    public Comments responseToComment(CommentResponse obj) throws ResourceNotFoundException;

}
