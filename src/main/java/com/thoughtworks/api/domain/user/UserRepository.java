package com.thoughtworks.api.domain.user;

import java.util.List;
import java.util.Map;

public interface UserRepository {
  void create(Map<String, Object> info);

  User findById(int id);

  List<User> find();
}
