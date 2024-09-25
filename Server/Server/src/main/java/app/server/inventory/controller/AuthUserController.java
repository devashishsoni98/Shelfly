package app.server.inventory.controller;

import app.server.inventory.dtos.UserDto;
import app.server.inventory.entities.User;
import app.server.inventory.mapper.UserMapper;
import app.server.inventory.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AuthUserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User user = userService.createUser(userDto);
        UserDto createdUser = UserMapper.mapToUserDto(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
        User user = userService.getUserById(userId);
        UserDto foundUser = UserMapper.mapToUserDto(user);
        return ResponseEntity.ok(foundUser);
    }

    // Add more endpoints (e.g., update, delete, getAll) as needed
}
