package ru.maliutin.webclient.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class Product {

    private long productId;
    private String title;
    private String catalogueNumber;
    private int programNumber;
    private Set<Technic> technics;
    private int balance;
    private BigDecimal price;

}
