package com.thoughtworks.api.domain.user;

import com.thoughtworks.api.domain.order.Order;
import com.thoughtworks.api.infrastructure.mybatis.mappers.OrderMapper;
import com.thoughtworks.api.infrastructure.records.Record;
import com.thoughtworks.api.web.jersey.Routes;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class User implements Record {
  @Inject
  OrderMapper orderMapper;

  private int id;

  public User() {
  }

  public User(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  private String name;

  public void placeOrder(Map<String, Object> info) {
    orderMapper.save(info);
  }

  public Order findOrderById(int id) {
    return orderMapper.findById(id);
  }

  @Override
  public Map<String, Object> toRefJson(Routes routes) {
    return new HashMap<String, Object>() {{
      put("id", id);
      put("uri", "users/" + id);
      put("name", name);
    }};
  }

  @Override
  public Map<String, Object> toJson(Routes routes) {
    return toRefJson(routes);
  }
}
