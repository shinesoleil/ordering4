package com.thoughtworks.api.domain.order;

import com.thoughtworks.api.infrastructure.records.Record;
import com.thoughtworks.api.web.jersey.Routes;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Order implements Record {
  private int id;
  private String name;
  private String address;
  private String phone;
  private Date time;
  private List<OrderItem> orderItems;

  public Order() {
  }

  public Order(int id, String name, String address, String phone, Date time, List<OrderItem> orderItems) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.phone = phone;
    this.time = time;
    this.orderItems = orderItems;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }

  public Date getTime() {
    return time;
  }

  public List<OrderItem> getOrderItems() {
    return orderItems;
  }

  @Override
  public Map<String, Object> toRefJson(Routes routes) {
    return null;
  }

  @Override
  public Map<String, Object> toJson(Routes routes) {
    return null;
  }
}
