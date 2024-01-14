package com.matidominati.shoppingcartservice.shoppingcartservice.controller;

import com.matidominati.shoppingcartservice.shoppingcartservice.model.CartItem;
import com.matidominati.shoppingcartservice.shoppingcartservice.model.CartTO;
import com.matidominati.shoppingcartservice.shoppingcartservice.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private ShoppingCartService cartService;

    @GetMapping("/{cartId}")
    public CartTO getCart(@PathVariable Long cartId) {
        return cartService.getCart(cartId);
    }

    @GetMapping("/{cartId}/items")
    public List<CartItem> getCartItems(@PathVariable Long cartId) {
        return cartService.getCartItems(cartId);
    }

    @GetMapping("/{cartId}/totalPrice")
    public BigDecimal getTotalPrice(@PathVariable Long cartId) {
        return cartService.getTotalPrice(cartId);
    }

    @PostMapping("/{productId}/add")
    public CartTO addFirstProduct(@PathVariable Long productId, @RequestParam int quantity) {
        return cartService.addFirstProduct(productId, quantity);
    }

    @PostMapping("/{cartId}/add")
    public CartTO addAnotherProduct(@PathVariable Long cartId, @RequestParam Long productId, @RequestParam int quantity) {
        return cartService.addAnotherProduct(cartId, productId, quantity);
    }

    @DeleteMapping("/{cartId}/remove")
    public CartTO removeProduct(@PathVariable Long cartId, @RequestParam Long itemId) {
        return cartService.removeProduct(cartId, itemId);
    }

    @DeleteMapping("/{cartId}")
    public void deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
    }

    @DeleteMapping("/{cartId}/clear")
    public CartTO clearCart(@PathVariable Long cartId) {
        return cartService.clearCart(cartId);
    }
}
