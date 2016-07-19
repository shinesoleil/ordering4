package com.thoughtworks.api.web;

import com.thoughtworks.api.domain.order.Order;
import com.thoughtworks.api.domain.payment.Payment;
import com.thoughtworks.api.domain.user.User;
import com.thoughtworks.api.domain.user.UserRepository;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.HashMap;

@Path("users/{userId}/orders/{orderId}/payment")
public class PaymentApi {
  @Context
  UserRepository userRepository;

  @POST
  public Response createPayment(HashMap<String, Object> info,
                                @PathParam("userId") int userId,
                                @PathParam("orderId") int orderId) {
    User user = userRepository.findById(userId);
    Order order = user.findOrderById(orderId);

    order.pay(info);
    Payment payment = order.findPaymentByOrderId(Integer.valueOf(String.valueOf(info.get("order_id"))));

    if (payment != null) {
      return Response.status(201).build();
    } else {
      throw new WebApplicationException(Response.Status.BAD_REQUEST);
    }
  }
}
