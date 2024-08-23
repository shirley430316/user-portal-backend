package org.example.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.example.dto.UserDTO;
import org.example.dto.UserLoginDTO;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path="/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private HttpServletRequest request;

    @Operation
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> sendCode(@RequestParam String email) {
        try {
            userService.sendCode(email);
            return new ResponseEntity<>("Code has been sent. Expire in 5 minutes", HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @Operation(summary = "verify the code")
    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public Boolean verify(@RequestParam String email, @RequestParam String code) {
        return userService.verify(email, code);
    }

    @Operation(summary = "verify the code and store user info")
    @RequestMapping(path="/register/personal-info",method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO, @RequestHeader Boolean hasVerified){
        if (hasVerified) {
            return new ResponseEntity<>(userService.register(userDTO),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Please verify email first.", HttpStatus.TEMPORARY_REDIRECT);
        }
    }

    @Operation(summary = "user login")
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(@RequestBody UserLoginDTO userLoginDTO, HttpServletResponse response){
        return userService.login(userLoginDTO);
    }
}
