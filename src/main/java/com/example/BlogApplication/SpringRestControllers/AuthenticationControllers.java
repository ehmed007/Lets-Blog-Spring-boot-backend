package com.example.BlogApplication.SpringRestControllers;

import com.example.BlogApplication.DTOs.Request.AuthenticationRequest;
import com.example.BlogApplication.DTOs.Response.ResponseAPI;
import com.example.BlogApplication.DTOs.Request.UserRequest;
import com.example.BlogApplication.DTOs.Response.UserResponse;
import com.example.BlogApplication.Entities.Posts;
import com.example.BlogApplication.Entities.Users;
import com.example.BlogApplication.Exceptions.ResourceNotFoundException;
import com.example.BlogApplication.Repositories.UsersRepository;
import com.example.BlogApplication.Services.FileService;
import com.example.BlogApplication.Services.JwtService;
import com.example.BlogApplication.Services.PostsService;
import com.example.BlogApplication.Services.UsersService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthenticationControllers {

    @Autowired
    private UsersService usersService;

    @Autowired
    private FileService fileService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String home() {
        return "This is Home";
    }

    @PostMapping("/register")
    public ResponseEntity<UserRequest> register(@Valid @RequestBody UserRequest userRequest) {
        Users user = this.usersService.requestToUsers(userRequest,"NORMAL");
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        UserResponse response = this.usersService.usersToResponse(this.usersService.addUser(this.usersService.usersToRequest(user)));
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthenticationRequest request) {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        Users user = this.userRepository.findByUsername(request.getUsername()).get();
        UserResponse response = this.usersService.usersToResponse(user);
        String jwtToken = this.jwtService.generateToken(user);
        HashMap<String, Object> obj = new HashMap<>();
        obj.put("token",jwtToken);
        obj.put("user", response);
        return new ResponseEntity(obj, HttpStatus.OK);
    }

    @PostMapping("/usernameExist/{username}")
    public ResponseEntity<ResponseAPI> UsernameExist(@PathVariable String username) {
        if (this.usersService.userExist(username)) {
            return new ResponseEntity<>(new ResponseAPI("Username already Exist!","false"),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new ResponseAPI("Username does not Exist!","true"),HttpStatus.OK);
    }


    @Value("${project.userImage}")
    private String path;
    @PostMapping("/uploadUserImage/{userId}")
    public ResponseEntity<ResponseAPI> uploadUserImage(@PathVariable String userId, @RequestParam("image") MultipartFile file) throws ResourceNotFoundException, IOException {
        Users user = this.usersService.getOneUser(Integer.parseInt(userId));
        user.setImageUrl(this.fileService.UploadFile(path, file));
        this.usersService.updateUser(user,Integer.parseInt(userId));
        return new ResponseEntity<>(new ResponseAPI("Image Uploaded Successfully!","true"), HttpStatus.OK);
    }

    @GetMapping(value = "/getUserImage/{imageName}",produces = MediaType.ALL_VALUE)
    public void downloadUserImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        InputStream resource = this.fileService.getResource(path, imageName);
        response.setContentType(MediaType.ALL_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

}
