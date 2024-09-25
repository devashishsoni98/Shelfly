package app.server.inventory.mapper;

import app.server.inventory.dtos.UserDto;
import app.server.inventory.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static User mapToUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        return User.builder()
                .userId(userDto.getUserId())
                .name(userDto.getName())
                .role(userDto.getRole())
                .contact(userDto.getContact())
                .build();
    }

    public static UserDto mapToUserDto(User user) {
        if (user == null) {
            return null;
        }

        return UserDto.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .role(user.getRole())
                .contact(user.getContact())
                .build();
    }
}
