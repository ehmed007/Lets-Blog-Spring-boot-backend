package com.example.BlogApplication.SpringRestControllers;

import com.example.BlogApplication.DTOs.Response.ResponseAPI;
import com.example.BlogApplication.DTOs.Request.UserRequest;
import com.example.BlogApplication.DTOs.Response.UserResponse;
import com.example.BlogApplication.Entities.Users;
import com.example.BlogApplication.Exceptions.ResourceNotFoundException;
import com.example.BlogApplication.Services.FileService;
import com.example.BlogApplication.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UsersService usersService;
    @Autowired
    private FileService fileService;

    @GetMapping("/")
    public String home() {
        return "This is Home";
    }


    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest request , @PathVariable String userId) throws ResourceNotFoundException {
        Users user  = this.usersService.updateUser(request, Integer.parseInt(userId));
        UserResponse response = this.usersService.usersToResponse(user);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<ResponseAPI> deleteUser(@PathVariable String userId) throws ResourceNotFoundException {
        this.usersService.deleteUser(Integer.parseInt(userId));
        return new ResponseEntity<>(new ResponseAPI("User deleted successfully!","true"),HttpStatus.OK);
    }


    @GetMapping("/getAllUser")
    public List<UserResponse> getAllusers() {
        List<Users> list =this.usersService.getAlluser();
        List<UserResponse> userResponseList = list.stream().map(users -> this.usersService.usersToResponse(users)).collect(Collectors.toList());
        return userResponseList;
    }

    @GetMapping("/getOneUser/{userId}")
    public ResponseEntity<Users> getUser(@PathVariable String userId) throws ResourceNotFoundException {
        UserResponse response = this.usersService.usersToResponse(this.usersService.getOneUser(Integer.parseInt(userId)));
        return new ResponseEntity(this.usersService.getOneUser(Integer.parseInt(userId)), HttpStatus.OK);
    }



}
