package com.matidominati.shoppingcartservice.shoppingcartservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {
    private Long productId;
    private String productName;
    private int quantity;
    private BigDecimal productPrice;
}
