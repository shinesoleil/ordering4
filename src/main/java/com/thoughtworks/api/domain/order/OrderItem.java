package com.thoughtworks.api.domain.order;

import java.util.HashMap;
import java.util.Map;

public class OrderItem {
  private int orderId;
  private int productId;
  private int quantity;
  private double amount;

  public OrderItem() {
  }

  public OrderItem(int orderId, int productId, int quantity, double amount) {
    this.orderId = orderId;
    this.productId = productId;
    this.quantity = quantity;
    this.amount = amount;
  }

  public int getOrderId() {
    return orderId;
  }

  public int getProductId() {
    return productId;
  }

  public int getQuantity() {
    return quantity;
  }

  public double getAmount() {
    return amount;
  }

  public Map<String, Object> toJson() {
    return new HashMap<String, Object>() {{
      put("product_id", productId);
      put("quantity", quantity);
      put("amount", amount);
    }};
  }
}
