package com.example.username_pw_jwt.service;

import com.example.username_pw_jwt.domain.UserEntity;
import com.example.username_pw_jwt.domain.UserRepository;
import com.example.username_pw_jwt.dto.ResponseDto;
import com.example.username_pw_jwt.dto.SignUpDto;
import com.example.username_pw_jwt.dto.UserDetailsImpl;
import com.example.username_pw_jwt.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public ResponseDto<UserDetails> createUser(SignUpDto signUpDto) {
        UserEntity user = UserEntity.create(signUpDto, passwordEncoder);
        userRepository.save(user);
        UserDetails userDetails = new UserDetailsImpl(user);
        return ResponseDto.<UserDetails>builder().data(userDetails).build();
    }

    public String loginUser(SignUpDto signUpDto) {
        UserEntity user = userRepository.findByEmail(signUpDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 유저입니다."));
        if (!passwordEncoder.matches(signUpDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return tokenProvider.create(user);
    }
}
