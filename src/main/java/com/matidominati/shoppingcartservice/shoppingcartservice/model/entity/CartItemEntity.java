package com.matidominati.shoppingcartservice.shoppingcartservice.model.entity;

import com.matidominati.shoppingcartservice.shoppingcartservice.model.entity.CartEntity;
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
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private String productType;
    private String productDescription;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private CartEntity cart;
    private int quantity;
}
