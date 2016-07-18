package com.thoughtworks.api.web;

import com.thoughtworks.api.domain.product.ProductRepository;
import com.thoughtworks.api.support.ApiSupport;
import com.thoughtworks.api.support.ApiTestRunner;
import com.thoughtworks.api.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class ProductsApiTest extends ApiSupport {

  @Inject
  ProductRepository productRepository;

  @Test
  public void should_return_201_when_post_with_parameters() {
    Response post = post("products", TestHelper.productMap());

    assertThat(post.getStatus(), is(201));
  }

  @Test
  public void should_return_list_of_products_when_get_products() {
    productRepository.create(TestHelper.productMap());

    Response get = get("products");
    List<Map> productList = get.readEntity(List.class);

    assertThat(get.getStatus(), is(200));
    assertThat(productList.get(0).get("name"), is("desk"));
  }
}
