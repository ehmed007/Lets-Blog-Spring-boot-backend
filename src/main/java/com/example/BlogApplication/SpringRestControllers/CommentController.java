package com.example.BlogApplication.SpringRestControllers;

import com.example.BlogApplication.DTOs.Request.CommentRequest;
import com.example.BlogApplication.DTOs.Response.CommentResponse;
import com.example.BlogApplication.DTOs.Response.ResponseAPI;
import com.example.BlogApplication.Entities.Comments;
import com.example.BlogApplication.Entities.Posts;
import com.example.BlogApplication.Entities.Users;
import com.example.BlogApplication.Exceptions.ResourceNotFoundException;
import com.example.BlogApplication.Services.CommentsService;
import com.example.BlogApplication.Services.PostsService;
import com.example.BlogApplication.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CommentController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private PostsService postsService;

    @Autowired
    private CommentsService commentsService;

    @PostMapping("/addComment/{postId}/{userId}")
    public ResponseEntity<CommentResponse> addPost(@RequestBody CommentRequest request, @PathVariable String postId, @PathVariable String userId) throws ResourceNotFoundException {
        Users user = this.usersService.getOneUser(Integer.parseInt(userId));
        Posts post = this.postsService.getOnePost(Integer.parseInt(postId));

        Comments comments = this.commentsService.requestToComment(request);
        comments.setUsers(user);
        comments.setPosts(post);

        Comments comment = this.commentsService.addComment(comments);
        user.setSingleComments(comment);
        post.setSingleComment(comment);
        this.usersService.updateUser(user, user.getId());
        this.postsService.updatePost(post, post.getId());
        return new ResponseEntity<>(this.commentsService.commentToResponse(comment), HttpStatus.OK);
    }


    @DeleteMapping("/deleteComment/{commentId}")
    public ResponseEntity<ResponseAPI> deleteComment(@PathVariable String commentId) throws ResourceNotFoundException {
        this.commentsService.deleteComment(Integer.parseInt(commentId));
        return new ResponseEntity<>(new ResponseAPI("Comment Deleted Successfully!","true"),HttpStatus.OK);
    }

}
