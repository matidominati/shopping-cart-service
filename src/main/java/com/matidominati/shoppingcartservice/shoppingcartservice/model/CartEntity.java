package com.matidominati.shoppingcartservice.shoppingcartservice.model;

import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cart_id")
    private List<CartItemEntity> cartItemEntities;
    private String discountCode;
    private BigDecimal discountPercentage;
    @ElementCollection
    @CollectionTable(name = "cart_discounts", joinColumns = @JoinColumn(name = "cart_id"))
    @MapKeyColumn(name = "discount_key")
    @Column(name = "discount_value")
    private Map<String, BigDecimal> discounts;

    public static CartEntity create() {
        return CartEntity.builder()
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .cartItemEntities(new ArrayList<>())
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
