package com.matidominati.shoppingcartservice.shoppingcartservice.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ShoppingCartTO {
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private BigDecimal totalPrice;
    private List<CartItem> cartItems;
    boolean hasDiscountCode;
}
