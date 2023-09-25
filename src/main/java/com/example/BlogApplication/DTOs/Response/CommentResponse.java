package com.example.BlogApplication.DTOs.Response;


import java.util.Date;

public class CommentResponse {
    private Integer id;
    private String comment;
    private Date date;
    private UserResponse userResponse;

    public CommentResponse() {
        super();
    }

    public CommentResponse(Integer id, String comment, Date date, UserResponse userResponse) {
        this.id = id;
        this.comment = comment;
        this.date = date;
        this.userResponse = userResponse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
}
