package com.matidominati.shoppingcartservice.shoppingcartservice.model.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class CartItemDto {
    private Long id;
    private String productName;
    private String productType;
    private String productDescription;
    private int quantity;
    private BigDecimal price;
}
