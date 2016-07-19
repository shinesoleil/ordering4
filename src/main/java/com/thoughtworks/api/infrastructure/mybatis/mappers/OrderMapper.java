package com.thoughtworks.api.infrastructure.mybatis.mappers;

import com.thoughtworks.api.domain.order.Order;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface OrderMapper {
  void save(@Param("info") Map<String, Object> info);

  Order findById(@Param("id") int id);
}
