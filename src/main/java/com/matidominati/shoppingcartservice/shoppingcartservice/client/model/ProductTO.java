package com.matidominati.shoppingcartservice.shoppingcartservice.client.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class ProductTO {
    private Long id;
    private String productName;
    private String productType;
    private String productDescription;
    private BigDecimal basePrice;
}

