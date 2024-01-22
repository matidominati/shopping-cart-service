package com.matidominati.shoppingcartservice.shoppingcartservice.utils;

import com.matidominati.shoppingcartservice.shoppingcartservice.model.entity.CartEntity;
import com.matidominati.shoppingcartservice.shoppingcartservice.model.entity.CartItemEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.matidominati.shoppingcartservice.shoppingcartservice.model.entity.CartEntity.create;
import static com.matidominati.shoppingcartservice.shoppingcartservice.utils.CostUtils.calculateTotalPrice;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CartUtils {
    public static Optional<CartItemEntity> findItemInCart(CartEntity cart, Long itemId) {
        return cart.getCartItems().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst();
    }

    public static CartEntity removeItemAndUpdateCart(CartEntity cart, CartItemEntity itemToRemove) {
        cart.getCartItems().remove(itemToRemove);
        cart.setTotalPrice(calculateTotalPrice(cart));
        cart.setModifiedAt(LocalDateTime.now());
        return cart;
    }
    public static void addToCartAndUpdate(CartEntity cart, CartItemEntity newItem) {
        cart.getCartItems().add(newItem);
        cart.setTotalPrice(calculateTotalPrice(cart));
        cart.setModifiedAt(LocalDateTime.now());
    }

    public static boolean isDiscountCodeValid(CartEntity cart, String discountCode) {
        return cart.getDiscounts() != null && cart.getDiscounts().containsKey(discountCode);
    }

    public static void applyDiscountToCart(CartEntity cart, String discountCode) {
        BigDecimal discountPercentage = cart.getDiscounts().get(discountCode);
        BigDecimal discountAmount = cart.getTotalPrice().multiply(discountPercentage);

        cart.setDiscountCode(discountCode);
        cart.setDiscountPercentage(discountPercentage);
        cart.setTotalPrice(cart.getTotalPrice().subtract(discountAmount));
    }

}
