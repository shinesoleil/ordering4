package com.thoughtworks.api.web;

import com.thoughtworks.api.domain.order.Order;
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
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class PaymentApiTest extends ApiSupport{

  @Inject
  ProductRepository productRepository;

  @Inject
  UserRepository userRepository;

  @Test
  public void should_return_201_when_post_payment_with_parameters() {

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

    Map<String, Object> paymentInfo = TestHelper.paymentMap(orderId);

    Response post = post("users/" + userId + "/orders/" + orderId + "/payment", paymentInfo);

    assertThat(post.getStatus(), is(201));
  }

  @Test
  public void should_return_payment_when_get_payment_by_order_id() {
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
    order.pay(TestHelper.paymentMap(orderId));

    Response get = get("users/" + userId + "/orders/" + orderId + "/payment");
    Map<String, Object> payment = get.readEntity(Map.class);

    assertThat(get.getStatus(), is(200));
    assertThat(payment.get("pay_type"), is("CASH"));
  }

  @Test
  public void should_return_404_when_get_payment_by_order_id_fails() {
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

    Response get = get("users/" + userId + "/orders/" + orderId + "/payment");

    assertThat(get.getStatus(), is(404));

  }

}
