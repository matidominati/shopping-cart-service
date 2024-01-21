package com.matidominati.shoppingcartservice.shoppingcartservice.client.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDto {
    private Long id;
    private String productName;
    private String productType;
    private String productDescription;
    private BigDecimal basePrice;
    private BigDecimal totalPrice;
    private List<AccessoryDto> accessories;
    private List<ConfigurationDto> configurations;
}

