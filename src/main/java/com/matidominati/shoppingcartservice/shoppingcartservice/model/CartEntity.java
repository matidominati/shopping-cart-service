package com.matidominati.shoppingcartservice.shoppingcartservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private BigDecimal totalPrice;
    private List<CartItem> cartItems;
    private String discountCode;

    public static CartEntity create() {
        return CartEntity.builder()
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .cartItems(new ArrayList<>())
                .discountCode(null)
                .totalPrice(BigDecimal.ZERO)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartEntity shoppingCart = (CartEntity) o;
        return id != null && Objects.equals(id, shoppingCart.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash();
    }
}