package com.kieran.jwt.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationDto {

    private String username;
    private String password;

    public String toString() {
        return "registration info: " + username + ", " + password;
    }
}
