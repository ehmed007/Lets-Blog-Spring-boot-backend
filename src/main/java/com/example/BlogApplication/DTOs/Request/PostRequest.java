package com.example.BlogApplication.DTOs.Request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PostRequest {

    private Integer id;
    @NotEmpty
    @Size(min = 10,max=1000, message = "Title should be minimum 10 and maximum 1000 characters!")
    private String title;
    @NotEmpty
    @Size(min = 50,max=80000, message = "Content should be minimum 50 and maximum 800000 characters!")
    private String content;

    public PostRequest() {
        super();
    }

    public PostRequest(Integer id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
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

}
