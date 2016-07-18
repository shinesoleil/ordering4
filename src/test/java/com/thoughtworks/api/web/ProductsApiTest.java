package com.thoughtworks.api.web;

import com.thoughtworks.api.support.ApiSupport;
import com.thoughtworks.api.support.ApiTestRunner;
import com.thoughtworks.api.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class ProductsApiTest extends ApiSupport {

  @Test
  public void should_return_201_when_post_with_parameters() {
    Response post = post("products", TestHelper.productMap());

    assertThat(post.getStatus(), is(201));
  }

  @Test
  public void should_return_200_when_get_products() {
    Response get = get("products");

    assertThat(get.getStatus(), is(200));
  }
}
