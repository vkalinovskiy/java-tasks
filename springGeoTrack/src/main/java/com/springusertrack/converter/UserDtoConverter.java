package com.springusertrack.converter;

import com.springusertrack.dto.UserDto;
import com.springusertrack.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter implements DtoConverter<UserDto, User> {
    @Override
    public User convertToEntity(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        return user;
    }

    @Override
    public UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());

        return dto;
    }
}
