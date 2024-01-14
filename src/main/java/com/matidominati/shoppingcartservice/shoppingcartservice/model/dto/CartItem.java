package com.matidominati.shoppingcartservice.shoppingcartservice.model.dto;

import com.matidominati.shoppingcartservice.shoppingcartservice.model.CartEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private String productName;
    private int quantity;
    private BigDecimal productPrice;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private CartEntity cart;
}
