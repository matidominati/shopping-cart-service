package com.matidominati.shoppingcartservice.shoppingcartservice.repository;

import com.matidominati.shoppingcartservice.shoppingcartservice.model.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Long> {

}
