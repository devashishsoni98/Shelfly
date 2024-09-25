//package app.server.inventory.auth.service;
//
//
//import app.server.inventory.auth.dto.LoginUserDto;
//import app.server.inventory.auth.dto.RegisterUserDto;
//import app.server.inventory.auth.mapper.UserMapper;
//import app.server.inventory.auth.entities.User;
//import app.server.inventory.auth.repository.UserRepository;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class AuthenticationService {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final AuthenticationManager authenticationManager;
//    private final UserProfileRepository userProfileRepository;
//
//    public AuthenticationService(
//            UserRepository userRepository,
//            AuthenticationManager authenticationManager,
//            PasswordEncoder passwordEncoder, UserProfileRepository userProfileRepository
//    ) {
//        this.authenticationManager = authenticationManager;
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.userProfileRepository = userProfileRepository;
//    }
//
//    public RegisterUserDto signup(RegisterUserDto input) {
//        var user = UserMapper.mapToUser(input, passwordEncoder.encode(input.getPassword()));
//        UserProfile userProfile = RegisterProfileMapper.mapToUserProfile(user);
//        userProfileRepository.save(userProfile);
//        userRepository.save(user);
//        return UserMapper.mapToUser(user, passwordEncoder.encode(user.getPassword()));
//    }
//
//    public User authenticate(LoginUserDto input) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        input.getEmail(),
//                        input.getPassword()
//                )
//        );
//
//        return userRepository.findByEmail(input.getEmail()).orElseThrow();
//    }
//
//    public List<User> allUsers() {
//        List<User> users = new ArrayList<>();
//
//        userRepository.findAll().forEach(users::add);
//
//        return users;
//    }
//}
package app.server.inventory.auth.service;

import app.server.inventory.auth.dto.LoginUserDto;
import app.server.inventory.auth.dto.RegisterUserDto;
import app.server.inventory.auth.mapper.AuthUserMapper;
import app.server.inventory.auth.entities.AuthUser;
import app.server.inventory.auth.repository.AuthUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService {

    private final AuthUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            AuthUserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginUserDto signup(RegisterUserDto input) {
        var user = AuthUserMapper.mapToUser(input, passwordEncoder.encode(input.getPassword()));
        userRepository.save(user);
        return AuthUserMapper.mapToUserDto(user);
    }

    public AuthUser authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }

    public List<AuthUser> allUsers() {
        List<AuthUser> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }
}
