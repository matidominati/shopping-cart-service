package com.matidominati.shoppingcartservice.shoppingcartservice.service;

import com.matidominati.shoppingcartservice.shoppingcartservice.client.ProductClient;
import com.matidominati.shoppingcartservice.shoppingcartservice.client.model.ProductTO;
import com.matidominati.shoppingcartservice.shoppingcartservice.mapper.CartMapper;
import com.matidominati.shoppingcartservice.shoppingcartservice.mapper.ProductMapper;
import com.matidominati.shoppingcartservice.shoppingcartservice.model.CartEntity;
import com.matidominati.shoppingcartservice.shoppingcartservice.model.CartItem;
import com.matidominati.shoppingcartservice.shoppingcartservice.model.CartTO;
import com.matidominati.shoppingcartservice.shoppingcartservice.repository.ShoppingCartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShoppingCartService {

    private final ProductClient productClient;
    private final ShoppingCartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductMapper productMapper;

    public CartTO getCart(Long id) {
        log.info("Process of displaying the contents of the shopping cart has started.");
        CartEntity cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shopping cart not found."));
        return cartMapper.map(cart);
    }

    @Transactional
    public CartTO addFirstProduct(Long productId, int quantity) {
        Optional<ProductTO> productTO = productClient.getProductById(productId);
        CartItem item = productMapper.map(productTO.get());
        CartEntity cart = createCart();
        item.setQuantity(quantity);
        cart.getCartItems().add(item);
        cart.setTotalPrice(calculateTotalPrice(cart));
        cartRepository.save(cart);
        return cartMapper.map(cart);
    }

    @Transactional
    public CartTO addAnotherProduct(Long cartId, Long productId, int quantity) {
        CartEntity cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Shopping cart not found."));
        Optional<ProductTO> productTO = productClient.getProductById(productId);
        CartItem newItem = productMapper.map(productTO.get());
        newItem.setQuantity(quantity);
        cart.getCartItems().add(newItem);
        cart.setTotalPrice(calculateTotalPrice(cart));
        cart.setModifiedAt(LocalDateTime.now());
        cartRepository.save(cart);
        return cartMapper.map(cart);
    }

    @Transactional
    public CartTO removeProduct(Long cartId, Long itemId) {
        CartEntity cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Shopping cart not found."));

        Optional<CartItem> itemToRemove = cart.getCartItems().stream()
                .filter(item -> item.getProductId().equals(itemId))
                .findFirst();

        if (itemToRemove.isPresent()) {
            cart.getCartItems().remove(itemToRemove.get());
            cart.setTotalPrice(calculateTotalPrice(cart));
            cart.setModifiedAt(LocalDateTime.now());
            cartRepository.save(cart);
            log.info("Product removed from cart. Cart ID: {}, Product ID: {}.", cartId, itemId);
        } else {
            log.warn("Product not found in cart. Cart ID: {}, Product ID: {}.", cartId, itemId);
        }
        return cartMapper.map(cart);
    }

    @Transactional
    public void deleteCart(Long cartId) {
        Optional<CartEntity> cartToDelete = cartRepository.findById(cartId);
        if (cartToDelete.isEmpty()) {
            throw new RuntimeException("Cart with given ID does not exist.");
        }
        cartRepository.delete(cartToDelete.get());
        log.info("Cart with ID: {} has ben deleted.", cartId);
    }

    private CartEntity createCart() {
        log.info("Process of creating shopping cart has started.");
        CartEntity cart = CartEntity.create();
        cartRepository.save(cart);
        log.info("Process of creating shopping cart with ID: {}  has been completed.", cart.getId());
        return cart;
    }

    private BigDecimal calculateTotalPrice(CartEntity cart) {
        return cart.getCartItems().stream()
                .map(item -> item.getProductPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
