package com.matidominati.shoppingcartservice.shoppingcartservice.controller;

import com.matidominati.shoppingcartservice.shoppingcartservice.model.dto.CartDto;
import com.matidominati.shoppingcartservice.shoppingcartservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @GetMapping("/{cartId}")
    public CartDto getCart(@PathVariable Long cartId) {
        return cartService.getCart(cartId);
    }

    @GetMapping("/{cartId}/totalPrice")
    public BigDecimal getTotalPrice(@PathVariable Long cartId) {
        return cartService.getTotalPrice(cartId);
    }

    @PostMapping("/{productId}")
    public CartDto addFirstProduct(@PathVariable Long productId,
                                   @RequestParam(defaultValue = "1") Integer quantity,
                                   @RequestParam(required = false)
                                   List<Long> selectedConfigurationIds,
                                   @RequestParam(required = false)
                                   List<Long> selectedAccessoryIds) {
        return cartService.addFirstProduct(productId, quantity, selectedConfigurationIds, selectedAccessoryIds);
    }

    @PatchMapping("/{cartId}")
    public CartDto addProduct(@PathVariable Long cartId,
                              @RequestParam Long productId,
                              @RequestParam(defaultValue = "1") Integer quantity,
                              @RequestParam(required = false)
                              List<Long> selectedConfigurationIds,
                              @RequestParam(required = false)
                              List<Long> selectedAccessoryIds) {
        return cartService.addProduct(cartId, productId, quantity, selectedConfigurationIds, selectedAccessoryIds);
    }

    @PatchMapping("/{cartId}/code")
    public CartDto applyDiscountCode(@PathVariable Long cartId, @RequestParam String discountCode) {
        return cartService.applyDiscountCode(cartId, discountCode);
    }

    @PatchMapping("/{cartId}/remove")
    public CartDto removeProduct(@PathVariable Long cartId, @RequestParam Long itemId) {
        return cartService.removeProduct(cartId, itemId);
    }

    @DeleteMapping("/{cartId}")
    public void deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
    }

    @DeleteMapping("/{cartId}/clear")
    public CartDto clearCart(@PathVariable Long cartId) {
        return cartService.clearCart(cartId);
    }
}
