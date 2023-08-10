package com.kieran.jwt.dtos;

import com.kieran.jwt.models.ApplicationUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {

    private ApplicationUser user;
    private String jwt;
}
