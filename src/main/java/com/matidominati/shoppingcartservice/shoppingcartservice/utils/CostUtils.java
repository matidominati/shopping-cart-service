package com.matidominati.shoppingcartservice.shoppingcartservice.utils;

import com.matidominati.shoppingcartservice.shoppingcartservice.model.entity.CartEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CostUtils {
    public static BigDecimal calculateTotalPrice(CartEntity cart) {
        return cart.getCartItems().stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
