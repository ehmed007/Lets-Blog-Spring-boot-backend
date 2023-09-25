package com.example.BlogApplication.Services;

import com.example.BlogApplication.DTOs.Request.CommentRequest;
import com.example.BlogApplication.DTOs.Response.CommentResponse;
import com.example.BlogApplication.Entities.Comments;
import com.example.BlogApplication.Exceptions.ResourceNotFoundException;
import com.example.BlogApplication.Repositories.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private PostsService postsService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private CommentsRepository commentsRepository;

    @Override
    public Comments addComment(CommentRequest obj) throws ResourceNotFoundException {
        return this.commentsRepository.save(this.requestToComment(obj));
    }

    @Override
    public Comments addComment(Comments obj) {
        return this.commentsRepository.save(obj);
    }

    @Override
    public Comments updateComment(CommentRequest obj, Integer commentId) throws ResourceNotFoundException {
        Comments comments = this.commentsRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException(commentId + " commentId is not found from Comment","Comment","commentId",commentId));
        comments.setId(obj.getId());
        comments.setComment(obj.getComment());
        return this.commentsRepository.save(comments);
    }

    @Override
    public Comments updateComment(Comments obj, Integer commentId) throws ResourceNotFoundException {
        Comments comments = this.commentsRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException(commentId + " commentId is not found from Comment","Comment","commentId",commentId));
        comments.setId(obj.getId());
        comments.setComment(obj.getComment());
        comments.setDate(obj.getDate());
        comments.setPosts(obj.getPosts());
        comments.setUsers(obj.getUsers());
        return this.commentsRepository.save(comments);
    }

    @Override
    public void deleteComment(Integer commentId) throws ResourceNotFoundException {
        Comments comments = this.commentsRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException(commentId + " commentId is not found from Comment","Comment","commentId",commentId));
        this.commentsRepository.delete(comments);
    }

    @Override
    public Comments getOneComment(Integer commentId) throws ResourceNotFoundException {
        Comments comments = this.commentsRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException(commentId + " commentId is not found from Comment","Comment","commentId",commentId));
        return comments;
    }

    @Override
    public List<Comments> getAllComment() {
        return this.commentsRepository.findAll();
    }

    @Override
    public CommentRequest commentToRequest(Comments obj) {
        CommentRequest request = new CommentRequest();
        request.setId(obj.getId());
        request.setComment(obj.getComment());
        return request;
    }

    @Override
    public Comments requestToComment(CommentRequest obj) throws ResourceNotFoundException {
        Comments comments = new Comments();
        comments.setId(obj.getId());
        comments.setComment(obj.getComment());
        comments.setDate(new Date(System.currentTimeMillis()));
        return comments;
    }

    @Override
    public CommentResponse commentToResponse(Comments obj) {
        CommentResponse response = new CommentResponse();
        response.setId(obj.getId());
        response.setComment(obj.getComment());
        response.setDate(obj.getDate());
        response.setUserResponse(this.usersService.usersToResponse(obj.getUsers()));
        return response;
    }

    @Override
    public Comments responseToComment(CommentResponse obj) throws ResourceNotFoundException {
        Comments comments = new Comments();
        comments.setId(obj.getId());
        comments.setComment(obj.getComment());
        comments.setDate(obj.getDate());
        comments.setUsers(this.usersService.responseToUsers(obj.getUserResponse()));
        return comments;
    }
}
