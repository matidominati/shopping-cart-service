package com.matidominati.shoppingcartservice.shoppingcartservice.repository;

import com.matidominati.shoppingcartservice.shoppingcartservice.model.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

}
