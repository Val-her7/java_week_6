package dev.val.COGIP_API.controller;

import dev.val.COGIP_API.configuration.JwtUtils;
import dev.val.COGIP_API.dto.LoginRequestDTO;
import dev.val.COGIP_API.dto.RegisterRequestDTO;
import dev.val.COGIP_API.dto.UserResponseDTO;
import dev.val.COGIP_API.repository.UserRepository;
import dev.val.COGIP_API.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RegistrationLoginController {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        if (userRepository.findByUsername(registerRequestDTO.username()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        UserResponseDTO createdUser = userService.createUser(registerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.username(), loginRequestDTO.password()));
            String token = jwtUtils.generateToken(loginRequestDTO.username());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
