package com.arindam.BookstoreAPI.controller;

import com.arindam.BookstoreAPI.entity.Users;
import com.arindam.BookstoreAPI.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "User Controller", description = "For accessing BookstoreAPI")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Registering a new customer", description = "Registering a customer for the API access")
    public Users register(@RequestBody Users user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    @Operation(summary = "Login a customer", description = "Logging in a customer to get the JWT token")
    public String login(@RequestBody Users user) {
        return userService.verify(user);
    }
}

// Steps to access this Bookstore ApiApplication with JWT:
// 1. Register with "/register" endpoint in post request by passing username and password
//   this will generate username and encrypted password
// 2. Login with "/login" endpoint in post request by passing the same username and password
//   this will generate the required JWT token
// 3. Pass the token to access Book and Customer APIs
