package com.example.demo.dto;


import lombok.Data;

@Data
public class RefreshRequest {

    int userId;
    String refreshToken;
}
