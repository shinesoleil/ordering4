package com.thoughtworks.api.web;

import com.thoughtworks.api.domain.user.UserRepository;
import com.thoughtworks.api.support.ApiSupport;
import com.thoughtworks.api.support.ApiTestRunner;
import com.thoughtworks.api.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

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
  public void should_return_200_when_get_users() {
    Response get = get("users");

    assertThat(get.getStatus(), is(200));
  }
}
