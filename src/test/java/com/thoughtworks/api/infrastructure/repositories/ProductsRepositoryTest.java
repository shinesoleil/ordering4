package com.thoughtworks.api.infrastructure.repositories;

import com.thoughtworks.api.domain.product.Product;
import com.thoughtworks.api.support.DatabaseTestRunner;
import com.thoughtworks.api.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(DatabaseTestRunner.class)
public class ProductsRepositoryTest {

  @Inject
  ProductRepository productRepository;

  @Test
  public void should_create_product_and_find_product_by_product_id() {
    Map<String, Object> productMap = TestHelper.productMap();

    productRepository.create(productMap);

    int id = Integer.valueOf(String.valueOf(productMap.get("id")));

    Product product = productRepository.findById(id);

    assertThat(product.getId(), is(id));
  }

}
