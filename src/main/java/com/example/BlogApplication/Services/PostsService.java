package com.example.BlogApplication.Services;


import com.example.BlogApplication.DTOs.Request.PostRequest;
import com.example.BlogApplication.DTOs.Response.PostResponse;
import com.example.BlogApplication.Entities.Posts;
import com.example.BlogApplication.Exceptions.ResourceNotFoundException;

import java.util.List;

public interface PostsService {
    public Posts addPost(PostRequest obj);

    public Posts addPost(Posts obj);

    public Posts updatePost(PostRequest obj, Integer postId) throws ResourceNotFoundException;

    public Posts updatePost(Posts obj, Integer postId) throws ResourceNotFoundException;

    public void deletePost(Integer postId) throws ResourceNotFoundException;
    public Posts getOnePost(Integer postId) throws ResourceNotFoundException;
    public List<Posts> getAllPost();

    public Posts requestToPost(PostRequest obj);

    public PostRequest postToRequest(Posts obj);
    public PostResponse postToResponse(Posts obj);
    public Posts responseToPost(PostResponse obj) throws ResourceNotFoundException;

    public List<Posts> getPostByCategoryId(Integer categoryId);
}
