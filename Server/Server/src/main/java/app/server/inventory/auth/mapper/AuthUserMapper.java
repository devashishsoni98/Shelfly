package app.server.inventory.auth.mapper;

import app.server.inventory.auth.dto.LoginUserDto;
import app.server.inventory.auth.dto.RegisterUserDto;
import app.server.inventory.auth.entities.AuthUser;

public class AuthUserMapper {

    // Convert User entity to LoginUserDto
    public static LoginUserDto mapToLoginUserDto(AuthUser user) {
        if (user == null) {
            return null;
        }
        return LoginUserDto.builder()
                .email(user.getEmail())
                .password(user.getPassword()) // Note: It's generally not advisable to expose the password
                .build();
    }

    // Convert RegisterUserDto to User entity
    public static AuthUser mapToUser(RegisterUserDto registerUserDto, String password) {
        if (registerUserDto == null) {
            return null;
        }
        return AuthUser.builder()
                .id(registerUserDto.getId()) // Assuming ID is optional for registration
                .email(registerUserDto.getEmail())
                .fullName(registerUserDto.getFullName())
                .password(password)
                .build();
    }

    // Convert User entity to RegisterUserDto
    public static RegisterUserDto mapToRegisterUserDto(AuthUser user) {
        if (user == null) {
            return null;
        }
        return RegisterUserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFullName())
                // Do not expose the password in DTOs
                .build();
    }

    // Convert User entity to LoginUserDto (for authentication)
    public static LoginUserDto mapToUserDto(AuthUser user) {
        if (user == null) {
            return null;
        }
        return LoginUserDto.builder()
                .email(user.getEmail())
                // Do not include password for security reasons
                // If you need other fields, add them here
                .build();
    }
}
