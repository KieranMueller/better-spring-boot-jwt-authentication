package com.kieran.jwt;

import com.kieran.jwt.models.ApplicationUser;
import com.kieran.jwt.models.Role;
import com.kieran.jwt.repositories.RoleRepository;
import com.kieran.jwt.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findByAuthority("ADMIN").isPresent()) return;

        Role adminRole = roleRepository.save(new Role(1L, "ADMIN"));
        roleRepository.save(new Role(2L, "USER"));

        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);

        ApplicationUser admin = new ApplicationUser(1L, "kieran", encoder.encode("pass"), roles);
        userRepository.save(admin);
    }

}
