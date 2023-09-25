package com.example.BlogApplication.SpringRestControllers;

import com.example.BlogApplication.DTOs.Request.PostRequest;
import com.example.BlogApplication.DTOs.Response.PostResponse;
import com.example.BlogApplication.DTOs.Response.ResponseAPI;
import com.example.BlogApplication.Entities.Category;
import com.example.BlogApplication.Entities.Posts;
import com.example.BlogApplication.Entities.Users;
import com.example.BlogApplication.Exceptions.ResourceNotFoundException;
import com.example.BlogApplication.Services.CategoryService;
import com.example.BlogApplication.Services.FileService;
import com.example.BlogApplication.Services.PostsService;
import com.example.BlogApplication.Services.UsersService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class PostController {
    @Autowired
    private PostsService postsService;

    @Autowired
    private FileService fileService;

    @Autowired
    private UsersService usersService;
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addPost/{userId}/{categoryId}")
    public ResponseEntity<PostResponse> addPost(@Valid @RequestBody PostRequest request, @PathVariable String userId,@PathVariable String categoryId) throws ResourceNotFoundException {
        Users user = this.usersService.getOneUser(Integer.parseInt(userId));
        Posts post = this.postsService.addPost(request);
        Category category = this.categoryService.getOneCategory(Integer.parseInt(categoryId));
        user.setSinglePosts(post);
        post.setUsers(user);
        post.setCategory(category);
        category.setSinglePosts(post);
        this.categoryService.addCategory(category);
        this.postsService.updatePost(this.postsService.postToRequest(post),post.getId());
        this.usersService.updateUser(this.usersService.usersToRequest(user),Integer.parseInt(userId));
        PostResponse response = this.postsService.postToResponse(post);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/updatePost/{postId}/{categoryId}")
    public ResponseEntity<PostResponse> updatePost(@Valid @RequestBody PostRequest request, @PathVariable String postId, @PathVariable String categoryId) throws ResourceNotFoundException {
        Posts post = this.postsService.requestToPost(request);
        post.setId(Integer.parseInt(postId));
        post.setCategory(this.categoryService.getOneCategory(Integer.parseInt(categoryId)));
        Posts post1 = this.postsService.updatePost(post, post.getId());
        PostResponse response = this.postsService.postToResponse(post1);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deletePost/{postId}")
    public ResponseEntity<ResponseAPI> deletePost(@PathVariable String postId) throws ResourceNotFoundException {
        this.postsService.deletePost(Integer.parseInt(postId));
        return new ResponseEntity<>(new ResponseAPI("Post deleted successfully!","true"),HttpStatus.OK);
    }

    @GetMapping("/getAllPost")
    public ResponseEntity<ResponseAPI> getAllPosts() {
        List<Posts> list = this.postsService.getAllPost();
        List<PostResponse> postResponseList = list.stream().map(post -> this.postsService.postToResponse(post)).collect(Collectors.toList());
        return new ResponseEntity<>(new ResponseAPI(postResponseList,"true"),HttpStatus.OK);
    }

    @GetMapping("/getAllPostByCategory/{categoryId}")
    public ResponseEntity<ResponseAPI> getAllPosts(@PathVariable String categoryId) {
        System.out.println(categoryId);
        List<Posts> list = this.postsService.getPostByCategoryId(Integer.parseInt(categoryId));
        List<PostResponse> postResponseList = list.stream().map(post -> this.postsService.postToResponse(post)).collect(Collectors.toList());
        return new ResponseEntity<>(new ResponseAPI(postResponseList,"true"),HttpStatus.OK);
    }

    @GetMapping("/getOnePost/{postId}")
    public ResponseEntity<PostResponse> getOnePost(@PathVariable String postId) throws ResourceNotFoundException {
        Posts post = this.postsService.getOnePost(Integer.parseInt(postId));
        PostResponse response = this.postsService.postToResponse(post);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @Value("${project.postImage}")
    private String path1;
    @PostMapping("/uploadPostImage/{postId}")
    public ResponseEntity<ResponseAPI> uploadPostImage(@PathVariable String postId,@RequestParam("image") MultipartFile file) throws ResourceNotFoundException, IOException {
        Posts post = this.postsService.getOnePost(Integer.parseInt(postId));
        post.setImageUrl(this.fileService.UploadFile(path1,file));
        this.postsService.updatePost(post, Integer.parseInt(postId));
        return new ResponseEntity<>(new ResponseAPI("Image Uploaded Successfully!","true"),HttpStatus.OK);
    }

    @GetMapping(value = "/getPostImage/{imageName}",produces = MediaType.ALL_VALUE)
    public void downloadPostImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        InputStream resource = this.fileService.getResource(path1, imageName);
        response.setContentType(MediaType.ALL_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }


}
