package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.domain.order.Order;
import com.thoughtworks.api.domain.user.User;
import com.thoughtworks.api.support.DatabaseTestRunner;
import com.thoughtworks.api.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(DatabaseTestRunner.class)
public class OrderManipulationTest {

  @Inject
  com.thoughtworks.api.domain.product.ProductRepository productRepository;

  @Inject
  com.thoughtworks.api.domain.user.UserRepository userRepository;

  @Test
  public void should_create_order_and_find_order_by_user_id_and_order_id() {
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

    Order order = user.findOrderById(orderId);

    assertThat(order.getId(), is(orderId));
    System.out.println(order.getOrderItems().get(0).getAmount() + "hahaha");
  }
}
