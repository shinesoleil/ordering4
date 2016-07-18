package com.thoughtworks.api.web;

import com.thoughtworks.api.domain.user.User;
import com.thoughtworks.api.domain.user.UserRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;

@Path("users")
public class UsersApi {

  @Inject
  UserRepository userRepository;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createUser(HashMap<String, Object> info) {
    userRepository.create(info);

    User user = userRepository.findById(Integer.valueOf(String.valueOf(info.get("id"))));

    if (user != null) {
      return Response.status(201).build();
    } else {
      throw new WebApplicationException(Response.Status.BAD_REQUEST);
    }
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<User> find() {
    return userRepository.find();
  }

  @GET
  @Path("{userId}")
  @Produces(MediaType.APPLICATION_JSON)
  public User findById(@PathParam("userId") int userId) {
    System.out.println(userId + "hahahaha");
    User user =  userRepository.findById(userId);

    if (user != null) {
      return user;
    } else {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
  }
}
