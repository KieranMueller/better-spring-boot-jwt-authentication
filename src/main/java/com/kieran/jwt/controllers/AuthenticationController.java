package com.kieran.jwt.controllers;

import com.kieran.jwt.dtos.LoginResponseDto;
import com.kieran.jwt.dtos.RegistrationDto;
import com.kieran.jwt.models.ApplicationUser;
import com.kieran.jwt.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDto body) throws RoleNotFoundException {
        return authenticationService.register(body.getUsername(), body.getPassword());
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody RegistrationDto body) {
        return authenticationService.login(body.getUsername(), body.getPassword());
    }
}
