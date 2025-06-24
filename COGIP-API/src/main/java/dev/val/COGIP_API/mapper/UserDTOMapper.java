package dev.val.COGIP_API.mapper;

import dev.val.COGIP_API.dto.UserDTO;
import dev.val.COGIP_API.model.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserDTOMapper implements Function<User, UserDTO> {

    @Override
    public UserDTO apply(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getUsername(),
                user.getRole().getName()
        );
    }
}
