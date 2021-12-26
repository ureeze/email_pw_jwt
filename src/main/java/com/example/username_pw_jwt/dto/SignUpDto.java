package com.example.username_pw_jwt.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {

    private String email;
    private String password;

}
