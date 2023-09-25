package com.example.BlogApplication.DTOs.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserRequest {
    private Integer id;
    @NotEmpty
    @Size(min = 5, message = "Username should be of min 5 characters!")
    private String username;
    @NotEmpty
    @Email(message = "Email address is not Valid!")
    private String email;
    @NotEmpty
    @Size(min = 5, message = "Firstname should be of min 5 characters!")
    private String firstname;
    @NotEmpty
    @Size(min = 5, message = "Lastname should be of min 5 characters!")
    private String lastname;
    @NotEmpty
    @Size(min = 5, message = "Password should be of min 5 characters!")
    private String password;
    @NotEmpty
    private String gender;

    public UserRequest() {
        super();
    }

    public UserRequest(Integer id, String username, String email, String firstname, String lastname, String password, String gender) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
