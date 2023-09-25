package com.example.BlogApplication.Exceptions;

import com.example.BlogApplication.DTOs.Response.ResponseAPI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseAPI> handleResourseNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new ResponseAPI(ex.getMessage(),"False"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseAPI> validationException(MethodArgumentNotValidException ex) {
        List<String> msgs = new ArrayList<>();
        ex.getAllErrors().forEach(i -> {
            msgs.add(i.getDefaultMessage());
        });
        return new ResponseEntity<>(new ResponseAPI(msgs,"False"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ResponseAPI> SQLIntegrity(SQLException ex) {
        List<String> msgs = new ArrayList<>();
        msgs.add("Username is Already Taken!");
        return new ResponseEntity<>(new ResponseAPI(msgs,"False"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ResponseAPI> usernameNotFound(UsernameNotFoundException ex) {
        List<String> msgs = new ArrayList<>();
        msgs.add("Username or Password is Incorrect!");
        return new ResponseEntity<>(new ResponseAPI(msgs,"False"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ResponseAPI> ImageNotFound(FileNotFoundException ex) {
        List<String> msgs = new ArrayList<>();
        msgs.add("Image does not Exist!");
        return new ResponseEntity<>(new ResponseAPI(msgs,"False"), HttpStatus.BAD_REQUEST);
    }
}
