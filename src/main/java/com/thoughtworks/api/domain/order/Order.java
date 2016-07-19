package com.thoughtworks.api.domain.order;

import com.thoughtworks.api.domain.payment.Payment;
import com.thoughtworks.api.infrastructure.mybatis.mappers.PaymentMapper;
import com.thoughtworks.api.infrastructure.records.Record;
import com.thoughtworks.api.web.jersey.Routes;

import javax.inject.Inject;
import java.util.*;

public class Order implements Record {
  @Inject
  PaymentMapper paymentMapper;

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

  public void pay(Map<String, Object> paymentInfo) {
    paymentMapper.save(paymentInfo);
  }

  public Payment findPaymentByOrderId(int orderId) {
    return paymentMapper.findByOrderId(orderId);
  }

  private double getTotalPrice() {
    double totalPrice = 0;
    for(OrderItem orderItem: orderItems) {
      totalPrice += orderItem.getAmount();
    }
    return totalPrice;
  }

  @Override
  public Map<String, Object> toRefJson(Routes routes) {
    return new HashMap<String, Object>() {{
      put("uri", "orders/" + id);
      put("name", name);
      put("address", address);
      put("phone", phone);
      put("created_at", time);
      put("total_price", getTotalPrice());
    }};
  }

  @Override
  public Map<String, Object> toJson(Routes routes) {
    List<Map<String, Object>> orderItemsMap = new ArrayList<Map<String, Object>>();
    for(OrderItem orderItem: orderItems) {
      orderItemsMap.add(orderItem.toJson());
    }

    return new HashMap<String, Object>() {{
      put("uri", "orders/" + id);
      put("name", name);
      put("address", address);
      put("phone", phone);
      put("created_at", time);
      put("total_price", getTotalPrice());
      put("order_items", orderItemsMap);
    }};
  }
}
