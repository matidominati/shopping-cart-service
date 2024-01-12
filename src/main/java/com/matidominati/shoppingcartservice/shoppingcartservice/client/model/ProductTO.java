package com.matidominati.shoppingcartservice.shoppingcartservice.client.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ProductTO {
    private Long productId;
}
