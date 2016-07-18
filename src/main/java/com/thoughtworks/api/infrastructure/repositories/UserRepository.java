package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.domain.user.User;
import com.thoughtworks.api.infrastructure.mybatis.mappers.UserMapper;

import javax.inject.Inject;
import java.util.Map;

public class UserRepository implements com.thoughtworks.api.domain.user.UserRepository {
  @Inject
  UserMapper userMapper;

  @Override
  public void create(Map<String, Object> info) {
    userMapper.save(info);
  }

  @Override
  public User findById(int id) {
    return userMapper.findById(id);
  }
}
