package com.example.BlogApplication.Services;


import com.example.BlogApplication.DTOs.Request.UserRequest;
import com.example.BlogApplication.DTOs.Response.UserResponse;
import com.example.BlogApplication.Entities.Users;
import com.example.BlogApplication.Exceptions.ResourceNotFoundException;

import java.util.List;

public interface UsersService {
    public Users addUser(UserRequest obj);
    public Users addUser(Users obj);

    public Users updateUser(UserRequest obj, Integer userId) throws ResourceNotFoundException;

    public Users updateUser(Users obj, Integer userId) throws ResourceNotFoundException;

    public void deleteUser(Integer userId) throws ResourceNotFoundException;
    public Users getOneUser(Integer userId) throws ResourceNotFoundException;
    public List<Users> getAlluser();
    public Users requestToUsers(UserRequest obj, String role);
    public UserRequest usersToRequest(Users obj);
    public UserResponse usersToResponse(Users obj);
    public Users responseToUsers(UserResponse obj) throws ResourceNotFoundException;

    public Boolean userExist(String username);
}
