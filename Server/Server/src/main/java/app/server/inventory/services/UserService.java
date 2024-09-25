package app.server.inventory.services;

import app.server.inventory.dtos.UserDto;
import app.server.inventory.entities.User;

import java.util.List;

public interface UserService {
    User createUser(UserDto userDto);
    User getUserById(Long userId);
    List<User> getAllUsers();
    User updateUser(Long userId, UserDto userDto);
    void deleteUser(Long userId);
}
