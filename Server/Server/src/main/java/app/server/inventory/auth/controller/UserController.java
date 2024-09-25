package app.server.inventory.auth.controller;

import app.server.inventory.auth.entities.AuthUser;
import app.server.inventory.auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("/me")
    public ResponseEntity<AuthUser> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser currentUser = (AuthUser) authentication.getPrincipal();
        System.out.println(currentUser);
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping
    public ResponseEntity<List<AuthUser>> allUsers() {
        List <AuthUser> users = userService.allUsers();
        return ResponseEntity.ok(users);
    }
}
