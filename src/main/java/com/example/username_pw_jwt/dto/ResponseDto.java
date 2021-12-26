package com.example.username_pw_jwt.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto<T> implements Serializable {

    private String error;
    private T data;
}
