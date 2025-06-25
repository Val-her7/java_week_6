package dev.val.COGIP_API.mapper;

import dev.val.COGIP_API.dto.UserResponseDTO;
import dev.val.COGIP_API.model.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserResponseDTOMapper implements Function<User, UserResponseDTO> {

    @Override
    public UserResponseDTO apply(User user) {
        return new UserResponseDTO(
                user.getUserId(),
                user.getUsername(),
                user.getRole().getName()
        );
    }
}
