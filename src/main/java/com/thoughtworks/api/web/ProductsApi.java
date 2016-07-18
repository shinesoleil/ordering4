package com.thoughtworks.api.web;

import com.thoughtworks.api.domain.product.Product;
import com.thoughtworks.api.domain.product.ProductRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;

@Path("products")
public class ProductsApi {

  @Context
  ProductRepository productRepository;

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createProduct(HashMap<String, Object> info) {
    productRepository.create(info);

    Product product = productRepository.findById(Integer.valueOf(String.valueOf(info.get("id"))));

    if (product != null) {
      return Response.status(201).build();
    } else {
      throw new WebApplicationException(Response.Status.BAD_REQUEST);
    }
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public List<Product> find() {
    return productRepository.find();
  }

  @GET
  @Path("{productId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Product findById(@PathParam("productId") String productId) {
    Product product =  productRepository.findById(Integer.valueOf(productId));

    if (product != null) {
      return product;
    } else {
      throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
  }
}
