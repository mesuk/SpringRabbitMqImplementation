package com.example.userservice.controller;

import com.example.userservice.domain.UserEntity;
import com.example.userservice.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customer")
@Api(tags = {"CustomerController"})
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/saveUser")
    public ResponseEntity<UserEntity> saveUser( @RequestBody UserEntity request) throws RuntimeException {
        return new ResponseEntity<>(userService.saveUser(request), HttpStatus.OK);
    }

    @GetMapping(value = "/fetchAllUser")
    public ResponseEntity<List<UserEntity>> fetchAllUser() throws RuntimeException {
        return new ResponseEntity<>(userService.fetchAllUser(), HttpStatus.OK);
    }
}
