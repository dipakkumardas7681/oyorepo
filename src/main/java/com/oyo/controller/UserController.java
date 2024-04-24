package com.oyo.controller;

import com.oyo.entity.User;
import com.oyo.paylaod.SignIn;
import com.oyo.paylaod.TokenResponse;
import com.oyo.paylaod.UserDto;
import com.oyo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/oyo")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody UserDto userDto){
        User user = userService.addUser(userDto);
        if(user != null){
            return new ResponseEntity<>("Sign-up successfully" , HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Something went wrong" , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody SignIn signIn){
        String token = userService.verify(signIn);
        if(token != null){
            TokenResponse tokenResponse = new TokenResponse();
            tokenResponse.setToken(token);
            return new ResponseEntity<>(tokenResponse , HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid credential" , HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @GetMapping("/profile")
    public ResponseEntity<?> getCurrentUserProfile(@AuthenticationPrincipal User user){
        return new ResponseEntity<>(user , HttpStatus.OK);
    }
}
