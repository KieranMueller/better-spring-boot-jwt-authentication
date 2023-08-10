package com.kieran.jwt.services;

import com.kieran.jwt.dtos.LoginResponseDto;
import com.kieran.jwt.models.ApplicationUser;
import com.kieran.jwt.models.Role;
import com.kieran.jwt.repositories.RoleRepository;
import com.kieran.jwt.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleNotFoundException;
import javax.naming.AuthenticationException;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public ApplicationUser register(String username, String password) throws RoleNotFoundException {
        Role userRole = roleRepository.findByAuthority("USER")
                .orElseThrow(() -> new RoleNotFoundException("unable to find role"));
        return userRepository.save(
                new ApplicationUser(
                        1L,
                        username,
                        encoder.encode(password),
                        new HashSet<>(Set.of(userRole))));
    }

    public LoginResponseDto login(String username, String password) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            String token = tokenService.generateJwt(auth);
            return new LoginResponseDto(userRepository.findByUsername(username).get(), token);
        } catch (Exception e) {
            e.printStackTrace();
            return new LoginResponseDto(null, "");
        }
    }

}
