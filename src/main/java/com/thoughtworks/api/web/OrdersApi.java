package com.thoughtworks.api.web;

import com.thoughtworks.api.domain.order.Order;
import com.thoughtworks.api.domain.user.User;
import com.thoughtworks.api.domain.user.UserRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.HashMap;

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
  public Response find() {
    return Response.status(200).build();
  }
}
