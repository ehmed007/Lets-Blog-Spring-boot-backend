package com.example.BlogApplication.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private String comment;
    @NotNull
    private Date date;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private Users users;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "postId",referencedColumnName = "id")
    private Posts posts;

    public Comments() {
        super();
    }

    public Comments(Integer id, String comment, Date date, Users users, Posts posts) {
        this.id = id;
        this.comment = comment;
        this.date = date;
        this.users = users;
        this.posts = posts;
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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Posts getPosts() {
        return posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }
}
