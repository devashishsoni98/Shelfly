package app.server.inventory.auth.controller;

import app.server.inventory.auth.dto.LoginUserDto;

import app.server.inventory.auth.dto.RegisterUserDto;
import app.server.inventory.auth.entities.AuthUser;
import app.server.inventory.auth.responses.LoginResponse;
import app.server.inventory.auth.service.AuthenticationService;
import app.server.inventory.auth.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<LoginUserDto> register(@RequestBody RegisterUserDto registerUserDto){
        LoginUserDto registeredUser = authenticationService.signup(registerUserDto);
//        UserProfile userProfile = RegisterProfileMapper.mapToUserProfile()
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto loginUserDto){

    AuthUser authenticatedUser = authenticationService.authenticate(loginUserDto);
    String jwtToken=jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse );

    }



}
