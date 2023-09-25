package com.example.BlogApplication.DTOs.Request;

public class CommentRequest {
    private Integer id;
    private String comment;

    public CommentRequest() {
        super();
    }

    public CommentRequest(Integer id, String comment) {
        this.id = id;
        this.comment = comment;
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
}
