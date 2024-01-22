package com.matidominati.shoppingcartservice.shoppingcartservice.repository;

import com.matidominati.shoppingcartservice.shoppingcartservice.model.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long> {
}
