package app.server.inventory.auth.service;

import app.server.inventory.auth.entities.AuthUser;
import app.server.inventory.auth.repository.AuthUserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final AuthUserRepository userRepository;

    public UserService(AuthUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<AuthUser> allUsers() {
        List<AuthUser> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }
}
