package com.example.BlogApplication.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Posts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Column(length=1000)
    private String title;
    @NotNull
    @Column(length=80000)
    private String content;

    private String imageUrl;
    @NotNull
    private Date date;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private Users users;


    @OneToMany(mappedBy = "posts",cascade = CascadeType.ALL)
    private List<Comments> comments;


    @ManyToOne()
    @JoinColumn(name="categoryId",referencedColumnName = "id")
    private Category category;

    public Posts() {
        super();
    }

    public Posts(Integer id, String title, String content, String imageUrl, Date date, Users users, List<Comments> comments, Category category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.date = date;
        this.users = users;
        this.comments = comments;
        this.category = category;
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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setSingleComment(Comments comments) {
        if (this.comments == null) {
            this.comments = new ArrayList<>();
        }
        this.comments.add(comments);
    }
}
