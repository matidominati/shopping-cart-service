package com.matidominati.shoppingcartservice.shoppingcartservice.client.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductConfigurationTO {
    private Long configurationId;
    private String configurationValue;
    private BigDecimal configurationPrice;
}
