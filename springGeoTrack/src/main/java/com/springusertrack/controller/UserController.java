package com.springusertrack.controller;

import com.springusertrack.converter.UserDtoConverter;
import com.springusertrack.dao.UserMapper;
import com.springusertrack.dto.UserDto;
import com.springusertrack.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserMapper userMapper;
    private final UserDtoConverter userDtoConverter;

    @Autowired
    public UserController(UserMapper userMapper, UserDtoConverter userDtoConverter) {
        this.userMapper = userMapper;
        this.userDtoConverter = userDtoConverter;
    }

    @GetMapping
    public List<UserDto> index() {
        List<User> users = userMapper.selectAll();

        return userDtoConverter.convertToDtoList(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> get(@PathVariable int id) {
        User user = userMapper.selectById(id);

        if(user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserDto dto = userDtoConverter.convertToDto(user);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public UserDto create(@Validated @RequestBody UserDto userDto) {
        User user = userDtoConverter.convertToEntity(userDto);
        userMapper.insert(user);

        return userDtoConverter.convertToDto(user);
    }

    @PutMapping
    public void update(@Validated @RequestBody UserDto userDto) {
        User user = userDtoConverter.convertToEntity(userDto);
        userMapper.update(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        userMapper.deleteById(id);
    }
}
