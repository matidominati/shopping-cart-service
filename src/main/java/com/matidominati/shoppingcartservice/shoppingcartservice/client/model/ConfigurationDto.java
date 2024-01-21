package com.matidominati.shoppingcartservice.shoppingcartservice.client.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ConfigurationDto {
    private Long configurationId;
    private String configurationValue;
    private BigDecimal configurationPrice;
    private String configurationType;
}
