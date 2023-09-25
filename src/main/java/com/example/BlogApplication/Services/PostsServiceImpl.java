package com.example.BlogApplication.Services;

import com.example.BlogApplication.DTOs.Request.PostRequest;
import com.example.BlogApplication.DTOs.Response.CategoryResponse;
import com.example.BlogApplication.DTOs.Response.CommentResponse;
import com.example.BlogApplication.DTOs.Response.PostResponse;
import com.example.BlogApplication.Entities.Comments;
import com.example.BlogApplication.Entities.Posts;
import com.example.BlogApplication.Exceptions.ResourceNotFoundException;
import com.example.BlogApplication.Repositories.PostsRepository;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PreRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class PostsServiceImpl implements PostsService {

    @Autowired
    private PostsRepository postsRepository;
    @Autowired
    private UsersService usersService;

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public Posts addPost(PostRequest obj) {
        return this.postsRepository.save(this.requestToPost(obj));
    }

    @Override
    public Posts addPost(Posts obj) {
        return this.postsRepository.save(obj);
    }

    @Override
    public Posts updatePost(PostRequest obj, Integer postId) throws ResourceNotFoundException {
        Posts post = this.postsRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(postId + " postid is not found from Posts","Post","postId",postId));
        post.setId(obj.getId());
        post.setTitle(obj.getTitle());
        post.setContent(obj.getContent());
        return this.postsRepository.save(post);
    }

    @Override
    public Posts updatePost(Posts obj, Integer postId) throws ResourceNotFoundException {
        Posts post = this.postsRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(postId + " postid is not found from Posts","Post","postId",postId));
        post.setId(obj.getId());
        post.setTitle(obj.getTitle());
        post.setContent(obj.getContent());
        post.setCategory(obj.getCategory());
        return this.postsRepository.save(post);
    }

    @Override
    public void deletePost(Integer postId) throws ResourceNotFoundException {
        Posts post = this.postsRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(postId + " postid is not found from Posts","Post","postId",postId));
        this.postsRepository.delete(post);
    }

    @Override
    public Posts getOnePost(Integer postId) throws ResourceNotFoundException {
        Posts post = this.postsRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException(postId + " postid is not found from Posts","Post","postId",postId));
        return post;
    }

    @Override
    public List<Posts> getAllPost() {
        List<Posts> list = this.postsRepository.findAll();
        Collections.sort(list,(n1, n2) -> {
            int f1 = n1.getId();
            int f2 = n2.getId();

            if(f1 != f2) {
                return f2 - f1;
            }
            return n2.getId() - n1.getId();
        });
        return list;
    }

    @Override
    public Posts requestToPost(PostRequest obj) {
        Posts post = new Posts();
        post.setId(obj.getId());
        post.setTitle(obj.getTitle());
        post.setContent(obj.getContent());
        post.setDate(new Date(System.currentTimeMillis()));
        return post;
    }

    @Override
    public PostRequest postToRequest(Posts obj) {
        PostRequest postRequest = new PostRequest();
        postRequest.setId(obj.getId());
        postRequest.setTitle(obj.getTitle());
        postRequest.setContent(obj.getContent());
        return postRequest;
    }


    @Override
    public Posts responseToPost(PostResponse obj) throws ResourceNotFoundException {
        Posts post = new Posts();
        post.setId(obj.getId());
        post.setTitle(obj.getTitle());
        post.setContent(obj.getContent());
        post.setImageUrl(obj.getImageUrl());
        post.setDate(obj.getDate());
        post.setUsers(this.usersService.responseToUsers(obj.getUserResponse()));
        List<Comments> commentsList = new ArrayList<>();
        if (obj.getCommentResponses() != null) {
            obj.getCommentResponses().forEach(i -> {
                try {
                    commentsList.add(this.commentsService.responseToComment(i));
                } catch (ResourceNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        post.setComments(commentsList);
        return post;
    }

    @Override
    public List<Posts> getPostByCategoryId(Integer categoryId) {
        List<Posts> posts = this.postsRepository.findAll();
        List<Posts> result = new ArrayList<>();

        posts.forEach(i -> {
            if (i.getCategory().getId() == categoryId) {
                    result.add(i);
            }
        });
        Collections.sort(result,(n1, n2) -> {
            int f1 = n1.getId();
            int f2 = n2.getId();

            if(f1 != f2) {
                return f2 - f1;
            }
            return n2.getId() - n1.getId();
        });
        return result;
    }

    @Override
    public PostResponse postToResponse(Posts obj) {
        PostResponse response = new PostResponse();
        response.setId(obj.getId());
        response.setTitle(obj.getTitle());
        response.setContent(obj.getContent());
        response.setDate(obj.getDate());
        response.setImageUrl(obj.getImageUrl());
        response.setUserResponse(this.usersService.usersToResponse(obj.getUsers()));
        List<CommentResponse> commentResponseList = new ArrayList<>();
        if (obj.getComments() != null) {
            obj.getComments().forEach(i -> {
                commentResponseList.add(this.commentsService.commentToResponse(i));
            });
        }
        response.setCommentResponses(commentResponseList);
        response.setCategoryResponses(this.categoryService.categoryToResponse(obj.getCategory()));
        return response;
    }


}
