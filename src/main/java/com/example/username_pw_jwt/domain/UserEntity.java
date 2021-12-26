package com.example.username_pw_jwt.domain;

import com.example.username_pw_jwt.dto.SignUpDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Getter
@Entity
@Builder
@Table(name = "USER")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String email;

    @Column
    String password;

    public static UserEntity create(SignUpDto signUpDto, PasswordEncoder passwordEncoder){
        String pw = passwordEncoder.encode(signUpDto.getPassword());
        return UserEntity.builder()
                .email(signUpDto.getEmail())
                .password(pw)
                .build();
    }
}
