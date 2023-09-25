package com.example.BlogApplication.DTOs.Response;


import java.util.Date;
import java.util.List;

public class PostResponse {
    private Integer id;
    private String title;
    private String content;
    private String imageUrl;
    private Date date;
    private UserResponse userResponse;

    private List<CommentResponse> commentResponses;

    private CategoryResponse categoryResponses;

    public PostResponse() {
        super();
    }

    public PostResponse(Integer id, String title, String content, String imageUrl, Date date, UserResponse userResponse, List<CommentResponse> commentResponses, CategoryResponse categoryResponses) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.date = date;
        this.userResponse = userResponse;
        this.commentResponses = commentResponses;
        this.categoryResponses = categoryResponses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }

    public List<CommentResponse> getCommentResponses() {
        return commentResponses;
    }

    public void setCommentResponses(List<CommentResponse> commentResponses) {
        this.commentResponses = commentResponses;
    }

    public CategoryResponse getCategoryResponses() {
        return categoryResponses;
    }

    public void setCategoryResponses(CategoryResponse categoryResponses) {
        this.categoryResponses = categoryResponses;
    }
}
