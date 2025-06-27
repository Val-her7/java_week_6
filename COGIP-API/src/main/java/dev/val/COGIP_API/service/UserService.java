package dev.val.COGIP_API.service;

import dev.val.COGIP_API.dto.RegisterRequestDTO;
import dev.val.COGIP_API.dto.UserResponseDTO;
import dev.val.COGIP_API.mapper.UserResponseDTOMapper;
import dev.val.COGIP_API.model.User;
import dev.val.COGIP_API.repository.RoleRepository;
import dev.val.COGIP_API.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserResponseDTOMapper userResponseDTOMapper;

    public UserResponseDTO createUser(RegisterRequestDTO registerRequestDTO) {
        User newUser = new User();
        newUser.setUsername(registerRequestDTO.username());
        newUser.setPassword(passwordEncoder.encode(registerRequestDTO.password()));
        newUser.setRole(roleRepository.findByName("USER").orElseThrow(() -> new RuntimeException("Default role 'USER' not found.")));

        userRepository.save(newUser);

        return userResponseDTOMapper.apply(newUser);

    }
}
