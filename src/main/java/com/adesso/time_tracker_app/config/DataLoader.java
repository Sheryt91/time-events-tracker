package com.adesso.time_tracker_app.config;

import com.adesso.time_tracker_app.entity.User;
import com.adesso.time_tracker_app.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public DataLoader(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        if (repo.findByUsername("user1").isEmpty()) {
            User user = new User();
            user.setUsername("user1");
            user.setPassword(encoder.encode("password"));
            repo.save(user);
        }
    }
}
