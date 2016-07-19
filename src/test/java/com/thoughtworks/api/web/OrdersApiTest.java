package com.thoughtworks.api.web;

import com.thoughtworks.api.domain.product.ProductRepository;
import com.thoughtworks.api.domain.user.User;
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
public class OrdersApiTest extends ApiSupport{

  @Inject
  ProductRepository productRepository;

  @Inject
  UserRepository userRepository;

  @Test
  public void should_return_201_when_post_with_parameters() {
    Map<String, Object> productInfo = TestHelper.productMap();
    Map<String, Object> userInfo = TestHelper.userMap();

    productRepository.create(productInfo);
    userRepository.create(userInfo);

    int productId = Integer.valueOf(String.valueOf(productInfo.get("id")));
    int userId = Integer.valueOf(String.valueOf(userInfo.get("id")));

    Response post = post("users/" + userId + "/orders", TestHelper.orderMap(userId, productId));

    assertThat(post.getStatus(), is(201));
  }

  @Test
  public void should_return_list_of_order_when_get_orders() {
    Map<String, Object> productInfo = TestHelper.productMap();
    Map<String, Object> userInfo = TestHelper.userMap();

    productRepository.create(productInfo);
    userRepository.create(userInfo);

    int productId = Integer.valueOf(String.valueOf(productInfo.get("id")));
    int userId = Integer.valueOf(String.valueOf(userInfo.get("id")));

    User user = userRepository.findById(userId);

    Map<String, Object> orderInfo = TestHelper.orderMap(userId, productId);
    user.placeOrder(orderInfo);
    int orderId = Integer.valueOf(String.valueOf(orderInfo.get("id")));

    Response get = get("users/" + userId + "/orders");

    List<Map> orderList = get.readEntity(List.class);

    assertThat(get.getStatus(), is(200));
    assertThat(orderList.get(0).get("name"), is("firstOrder"));
  }

}
