package com.adesso.time_tracker_app.config;

import com.adesso.time_tracker_app.entity.Role;
import com.adesso.time_tracker_app.entity.User;
import com.adesso.time_tracker_app.repository.RoleRepository;
import com.adesso.time_tracker_app.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public DataLoader(UserRepository repo, PasswordEncoder encoder, RoleRepository roleRepository) {
        this.userRepository = repo;
        this.passwordEncoder = encoder;
        this.roleRepository=roleRepository;
    }

    @Override
    public void run(String... args) {
        // Create or fetch ADMIN role
        Role adminRole = roleRepository.findByRoleName("ROLE_ADMIN")
                .orElseGet(() -> {
                    Role newAdminRole = new Role();
                    newAdminRole.setRoleName("ROLE_ADMIN");
                    return roleRepository.save(newAdminRole);
                });

        // Create or fetch USER role
        Role userRole = roleRepository.findByRoleName("ROLE_USER")
                .orElseGet(() -> {
                    Role newUserRole = new Role();
                    newUserRole.setRoleName("ROLE_USER");
                    return roleRepository.save(newUserRole);
                });

        // Create user if not exists
        if (userRepository.findByUsername("john_doe").isEmpty()) {
            User user = new User();
            user.setUsername("john_doe");
            user.setPassword(passwordEncoder.encode("password")); // Hash the password
            user.getRoles().add(adminRole);
            user.getRoles().add(userRole);
            userRepository.save(user);
        }
        // Create user if not exists
        if (userRepository.findByUsername("user1").isEmpty()) {
            User user = new User();
            user.setUsername("user1");
            user.setPassword(passwordEncoder.encode("password")); // Hash the password
            user.getRoles().add(userRole);
            userRepository.save(user);
        }
        // Create user if not exists
        if (userRepository.findByUsername("admin").isEmpty()) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("password")); // Hash the password
            user.getRoles().add(adminRole);
            userRepository.save(user);
        }
    }

}
