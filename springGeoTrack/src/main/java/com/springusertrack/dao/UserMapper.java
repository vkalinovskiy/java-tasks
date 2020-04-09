package com.springusertrack.dao;

import com.springusertrack.model.User;

import java.util.List;

public interface UserMapper {
    List<User> selectAll();

    User selectById(int id);

    int insert(User user);

    int update(User user);

    int delete(User user);

    int deleteById(int id);
}