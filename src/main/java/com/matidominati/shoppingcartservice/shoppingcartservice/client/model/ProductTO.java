package com.matidominati.shoppingcartservice.shoppingcartservice.client.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public class ProductTO {
    private Long id;
    private String productName;
    private String productType;
    private String productDescription;
    private BigDecimal basePrice;
    private BigDecimal totalPrice;
    private Map<String, ProductAccessoryTO> accessories;
    private Map<String, ProductConfigurationTO> configurations;
}

