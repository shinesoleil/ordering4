package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.domain.product.Product;
import com.thoughtworks.api.infrastructure.mybatis.mappers.ProductMapper;

import javax.inject.Inject;
import java.util.Map;

public class ProductRepository implements com.thoughtworks.api.domain.product.ProductRepository {
  @Inject
  ProductMapper productMapper;

  @Override
  public void create(Map<String, Object> info) {
    productMapper.save(info);
  }

  @Override
  public Product findById(int id) {
    return productMapper.findById(id);
  }
}
