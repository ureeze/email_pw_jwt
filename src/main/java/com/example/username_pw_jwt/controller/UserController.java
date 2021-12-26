package com.example.username_pw_jwt.controller;

import com.example.username_pw_jwt.dto.ResponseDto;
import com.example.username_pw_jwt.dto.SignUpDto;
import com.example.username_pw_jwt.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto> signup(@RequestBody SignUpDto signUpDto) {
        ResponseDto responseDto = userService.createUser(signUpDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> signin(@RequestBody SignUpDto signUpDto) {
        String token = userService.loginUser(signUpDto);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
