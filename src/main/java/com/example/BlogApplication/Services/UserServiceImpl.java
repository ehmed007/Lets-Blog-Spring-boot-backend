package com.example.BlogApplication.Services;

import com.example.BlogApplication.DTOs.Request.UserRequest;
import com.example.BlogApplication.DTOs.Response.UserResponse;
import com.example.BlogApplication.Entities.Users;
import com.example.BlogApplication.Exceptions.ResourceNotFoundException;
import com.example.BlogApplication.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Override
    public Users addUser(UserRequest obj) {
        Users user = this.requestToUsers(obj,"NORMAL");
        return this.usersRepository.save(user);
    }

    @Override
    public Users addUser(Users obj) {
        return this.usersRepository.save(obj);
    }


    @Override
    public Users updateUser(UserRequest obj, Integer userId) throws ResourceNotFoundException {
        Users user = this.usersRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(userId + " userid is not found from Users","User","userId",userId));
        user.setUsername(obj.getUsername());
        user.setFirstname(obj.getFirstname());
        user.setLastname(obj.getLastname());
        user.setPassword(obj.getPassword());
        user.setGender(obj.getGender());
        user.setEmail(obj.getEmail());
        return this.usersRepository.save(user);
    }

    @Override
    public Users updateUser(Users obj, Integer userId) throws ResourceNotFoundException {
        Users user = this.usersRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(userId + " userid is not found from Users","User","userId",userId));
        user.setUsername(obj.getUsername());
        user.setFirstname(obj.getFirstname());
        user.setLastname(obj.getLastname());
        user.setPassword(obj.getPassword());
        user.setGender(obj.getGender());
        user.setEmail(obj.getEmail());
        user.setImageUrl(obj.getImageUrl());
        user.setRole(obj.getRole());
        user.setComments(obj.getComments());
        user.setPosts(obj.getPosts());
        return this.usersRepository.save(user);
    }

    @Override
    public void deleteUser(Integer userId) throws ResourceNotFoundException {
        Users user = this.usersRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(userId + " userid is not found from Users","User","userId",userId));
        this.usersRepository.delete(user);
    }

    @Override
    public Users getOneUser(Integer userId) throws ResourceNotFoundException {
        Users user = this.usersRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException(userId + " userid is not found from Users","User","userId",userId));
        return user;
    }

    @Override
    public List<Users> getAlluser() {
        List<Users> list = this.usersRepository.findAll();
        return list;
    }


    @Override
    public Users requestToUsers(UserRequest obj, String role) {
        Users user = new Users();
        user.setId(obj.getId());
        user.setUsername(obj.getUsername());
        user.setEmail(obj.getEmail());
        user.setFirstname(obj.getFirstname());
        user.setLastname(obj.getLastname());
        user.setPassword(obj.getPassword());
        user.setGender(obj.getGender());
        user.setRole(role);
        return user;
    }

    @Override
    public UserRequest usersToRequest(Users obj) {
        UserRequest dto = new UserRequest();
        dto.setId(obj.getId());
        dto.setUsername(obj.getUsername());
        dto.setEmail(obj.getEmail());
        dto.setFirstname(obj.getFirstname());
        dto.setLastname(obj.getLastname());
        dto.setPassword(obj.getPassword());
        dto.setGender(obj.getGender());
        return dto;
    }

    @Override
    public UserResponse usersToResponse(Users obj) {
        UserResponse response = new UserResponse();
        response.setId(obj.getId());
        response.setUsername(obj.getUsername());
        response.setFirstname(obj.getFirstname());
        response.setLastname(obj.getLastname());
        response.setEmail(obj.getEmail());
        response.setGender(obj.getGender());
        response.setImageUrl(obj.getImageUrl());
        return response;
    }

    @Override
    public Users responseToUsers(UserResponse obj) throws ResourceNotFoundException {
        Users user = this.getOneUser(obj.getId());
        return user;
    }


    @Override
    public Boolean userExist(String username) {
        String obj = null;
        obj = this.usersRepository.findUserByUsername(username);
        if (obj == null) {
            return false;
        }
        return true;
    }

}
