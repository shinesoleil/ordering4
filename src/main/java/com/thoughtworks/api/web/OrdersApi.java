package com.thoughtworks.api.web;

import com.thoughtworks.api.domain.order.Order;
import com.thoughtworks.api.domain.user.User;
import com.thoughtworks.api.domain.user.UserRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;

@Path("users/{userId}/orders")
public class OrdersApi {

  @Context
  UserRepository userRepository;

  @POST
  public Response createOrder(HashMap<String, Object> info,
                              @PathParam("userId") int userId) {
    User user = userRepository.findById(userId);

    user.placeOrder(info);
    Order order = user.findOrderById(Integer.valueOf(String.valueOf(info.get("id"))));

    if (order != null) {
      return Response.status(201).build();
    } else {
      throw new WebApplicationException(Response.Status.BAD_REQUEST);
    }
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Order> find(@PathParam("userId") int userId) {
    User user = userRepository.findById(userId);

    return user.find();
  }

  @GET
  @Path("{orderId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Order findById(@PathParam("userId") int userId,
                        @PathParam("orderId") int orderId) {
    User user = userRepository.findById(userId);

    Order order = user.findOrderById(orderId);

    if (order != null) {
      return order;
    } else {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
  }
}
