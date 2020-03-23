package com.springusertrack.controller;

import com.springusertrack.dao.UserMapper;
import com.springusertrack.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserMapper userMapper;

    @Autowired
    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("")
    public List<User> index() {
        return userMapper.selectAll();
    }

    @GetMapping("/{id}")
    public User get(@PathVariable int id) {
        return userMapper.selectById(id);
    }

    @PostMapping("")
    public User create(@RequestBody User user) {
        userMapper.insert(user);
        return user;
    }

    @PutMapping("")
    public void update(@RequestBody User user) {
        userMapper.update(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        userMapper.deleteById(id);
    }
}
