package com.matidominati.shoppingcartservice.shoppingcartservice.service;

import com.matidominati.shoppingcartservice.shoppingcartservice.client.ProductClient;
import com.matidominati.shoppingcartservice.shoppingcartservice.client.model.ProductDto;
import com.matidominati.shoppingcartservice.shoppingcartservice.exception.DataNotFoundException;
import com.matidominati.shoppingcartservice.shoppingcartservice.mapper.CartMapper;
import com.matidominati.shoppingcartservice.shoppingcartservice.mapper.ProductMapper;
import com.matidominati.shoppingcartservice.shoppingcartservice.model.dto.CartDto;
import com.matidominati.shoppingcartservice.shoppingcartservice.model.entity.CartEntity;
import com.matidominati.shoppingcartservice.shoppingcartservice.model.entity.CartItemEntity;
import com.matidominati.shoppingcartservice.shoppingcartservice.repository.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.matidominati.shoppingcartservice.shoppingcartservice.utils.CartUtils.*;
import static com.matidominati.shoppingcartservice.shoppingcartservice.utils.CostUtils.calculateTotalPrice;
import static com.matidominati.shoppingcartservice.shoppingcartservice.utils.RepositoryUtils.findByIdOrThrow;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private final ProductClient productClient;
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductMapper productMapper;

    public CartDto getCart(Long cartId) {
        log.info("Process of displaying shopping cart with ID: {} has started.", cartId);
        CartEntity cart = findByIdOrThrow(cartId, cartRepository, CartEntity.class);
        return cartMapper.map(cart);
    }

    public BigDecimal getTotalPrice(Long cartId) {
        CartEntity cart = findByIdOrThrow(cartId, cartRepository, CartEntity.class);
        return cart.getTotalPrice();
    }

    @Transactional
    public CartDto addFirstProduct(Long productId, Integer quantity, List<Long> selectedConfigurationIds, List<Long> selectedAccessoryIds) {
        ProductDto baseProduct = productClient.customize(productId, selectedConfigurationIds, selectedAccessoryIds);
        CartItemEntity item = productMapper.map(baseProduct);
        cartRepository.save(createCartWithItem(item, quantity));
        return cartMapper.map(createCartWithItem(item, quantity));
    }

    @Transactional
    public CartDto addProduct(Long cartId, Long productId, Integer quantity, List<Long> selectedConfigurationIds, List<Long> selectedAccessoryIds) {
        CartEntity cart = findByIdOrThrow(cartId, cartRepository, CartEntity.class);
        ProductDto baseProduct = productClient.customize(productId, selectedConfigurationIds, selectedAccessoryIds);
        CartItemEntity newItem = productMapper.map(baseProduct);
        newItem.setQuantity(quantity);
        addToCartAndUpdate(cart, newItem);
        cartRepository.save(cart);
        return cartMapper.map(cart);
    }

    @Transactional
    public CartDto applyDiscountCode(Long cartId, String discountCode) {
        CartEntity cart = findByIdOrThrow(cartId, cartRepository, CartEntity.class);
        if (isDiscountCodeValid(cart, discountCode)) {
            applyDiscountToCart(cart, discountCode);
            cartRepository.save(cart);
            log.info("Discount code '{}' applied to cart with ID: {}. New total price: {}",
                    discountCode, cartId, cart.getTotalPrice());
        } else {
            log.warn("Invalid or not applicable discount code '{}' for cart with ID: {}", discountCode, cartId);
        }
        return cartMapper.map(cart);
    }

    @Transactional
    public CartDto removeProduct(Long cartId, Long itemId) {
        CartEntity cart = findByIdOrThrow(cartId, cartRepository, CartEntity.class);
        Optional<CartItemEntity> itemToRemove = findItemInCart(cart, itemId);
        if (itemToRemove.isPresent()) {
            removeItemAndUpdateCart(cart, itemToRemove.get());
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
            throw new DataNotFoundException("Cart with given ID does not exist.");
        }
        cartRepository.delete(cartToDelete.get());
        log.info("Cart with ID: {} has ben deleted.", cartId);
    }

    @Transactional
    public CartDto clearCart(Long cartId) {
        CartEntity cart = findByIdOrThrow(cartId, cartRepository, CartEntity.class);
        cart.getCartItems().clear();
        cart.setTotalPrice(BigDecimal.ZERO);
        cartRepository.save(cart);
        log.info("Shopping cart cleared. Cart ID: {}.", cartId);
        return cartMapper.map(cart);
    }

    private CartEntity createCart() {
        log.info("Process of creating shopping cart has started.");
        CartEntity cart = CartEntity.create();
        cartRepository.save(cart);
        log.info("Process of creating shopping cart with ID: {}  has been completed.", cart.getId());
        return cart;
    }

    private CartEntity createCartWithItem(CartItemEntity item, Integer quantity) {
        CartEntity cart = createCart();
        item.setQuantity(quantity);
        cart.getCartItems().add(item);
        cart.setTotalPrice(calculateTotalPrice(cart));
        return cart;
    }
}
