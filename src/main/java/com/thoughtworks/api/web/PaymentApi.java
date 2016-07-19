package com.thoughtworks.api.web;

import com.thoughtworks.api.domain.order.Order;
import com.thoughtworks.api.domain.payment.Payment;
import com.thoughtworks.api.domain.user.User;
import com.thoughtworks.api.domain.user.UserRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;

@Path("users/{userId}/orders/{orderId}/payment")
public class PaymentApi {
  @Context
  UserRepository userRepository;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
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

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Payment findByOrderId(@PathParam("userId") int userId,
                               @PathParam("orderId") int orderId) {
    User user = userRepository.findById(userId);
    Order order = user.findOrderById(orderId);
    return order.findPaymentByOrderId(orderId);
  }
}
