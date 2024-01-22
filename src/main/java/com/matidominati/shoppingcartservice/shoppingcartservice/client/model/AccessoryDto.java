package com.matidominati.shoppingcartservice.shoppingcartservice.client.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccessoryDto {
    private Long accessoryId;
    private String accessoryValue;
    private BigDecimal accessoryPrice;
    private String accessoryType;
}
