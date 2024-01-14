package com.matidominati.shoppingcartservice.shoppingcartservice.repository;

import com.matidominati.shoppingcartservice.shoppingcartservice.model.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<CartEntity, Long> {

}
