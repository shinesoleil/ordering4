package com.thoughtworks.api.web;

import com.thoughtworks.api.domain.user.UserRepository;
import com.thoughtworks.api.support.ApiSupport;
import com.thoughtworks.api.support.ApiTestRunner;
import com.thoughtworks.api.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class UserApiTest extends ApiSupport{

  @Inject
  UserRepository userRepository;

  @Test
  public void should_return_201_when_post_user_with_parameters() {
    Response post = post("users", TestHelper.userMap());

    assertThat(post.getStatus(), is(201));
  }

  @Test
  public void should_return_user_json_list_when_get_users() {
    userRepository.create(TestHelper.userMap());

    Response get = get("users");
    List<Map> userList = get.readEntity(List.class);

    assertThat(get.getStatus(), is(200));
    assertThat(userList.get(0).get("name"), is("firstUser"));
  }

  @Test
  public void should_return_user_json_when_get_user_by_id() {
    Map<String, Object> info = TestHelper.userMap();
    userRepository.create(info);
    int id = Integer.valueOf(String.valueOf(info.get("id")));

    Response get = get("users/" + id);
    Map user = get.readEntity(Map.class);

    assertThat(get.getStatus(), is(200));
    assertThat(user.get("name"), is("firstUser"));
  }
}
