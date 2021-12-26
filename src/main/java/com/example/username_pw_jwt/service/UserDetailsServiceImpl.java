package com.example.username_pw_jwt.service;

import com.example.username_pw_jwt.domain.UserEntity;
import com.example.username_pw_jwt.domain.UserRepository;
import com.example.username_pw_jwt.dto.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("가입되지 않은 유저입니다."));
        UserDetails userDetails = new UserDetailsImpl(user);
        return userDetails;
    }
}
