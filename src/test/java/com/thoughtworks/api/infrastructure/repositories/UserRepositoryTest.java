package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.domain.user.User;
import com.thoughtworks.api.domain.user.UserRepository;
import com.thoughtworks.api.support.DatabaseTestRunner;
import com.thoughtworks.api.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(DatabaseTestRunner.class)
public class UserRepositoryTest {

  @Inject
  UserRepository userRepository;

  @Test
  public void should_create_user_with_parameters_and_find_user_by_user_id() {
    Map<String, Object> info = TestHelper.userMap();

    userRepository.create(info);
    int id = Integer.valueOf(String.valueOf(info.get("id")));
    User user = userRepository.findById(id);

    assertThat(user.getId(), is(id));
  }

  @Test
  public void should_find_all_users() {
    Map<String, Object> info = TestHelper.userMap();

    userRepository.create(info);
    List<User> userList = userRepository.find();

    assertThat(userList.size(), is(1));
  }
}
