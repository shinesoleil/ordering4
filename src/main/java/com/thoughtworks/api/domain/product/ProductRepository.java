package com.thoughtworks.api.domain.product;

import java.util.Map;

public interface ProductRepository {
  void create(Map<String, Object> info);

  Product findById(int id);
}
