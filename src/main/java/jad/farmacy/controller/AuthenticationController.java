package jad.farmacy.controller;

import jad.farmacy.Entity.User;
import jad.farmacy.Repository.UserRepository;
import jad.farmacy.Service.AuthenticationService;
import jad.farmacy.Service.JwtService;
import jad.farmacy.configurations.GlobalResponse;
import jad.farmacy.dto.LoginUserDto;
import jad.farmacy.dto.RegisterUserDto;
import jad.farmacy.dto.Responses.LoginResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
        this.userRepository=userRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<GlobalResponse> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);
        GlobalResponse apiResponse = new GlobalResponse(200,"Registro Creado", "Registro Creado", registeredUser);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<GlobalResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);
        Optional<User> user = this.userRepository.findByEmail(loginUserDto.getEmail());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        loginResponse.setRole(authenticatedUser.getRole().getName().toString());
        loginResponse.setUser(user.orElse(null));

        GlobalResponse apiResponse = new GlobalResponse(200,"Registro Creado", "Registro Creado", loginResponse);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
