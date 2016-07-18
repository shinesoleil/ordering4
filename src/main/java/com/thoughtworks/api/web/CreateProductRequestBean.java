package com.thoughtworks.api.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CreateProductRequestBean {
  @JsonProperty
  private int id;

  @JsonProperty
  private String name;

  @JsonProperty
  private String description;

  @JsonProperty
  private double price;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public double getPrice() {
    return price;
  }
}
