package com.matidominati.shoppingcartservice.shoppingcartservice.client;

import com.matidominati.shoppingcartservice.shoppingcartservice.client.model.ProductTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "product-service", url = "http://localhost:8080")
public interface ProductClient {

    @GetMapping("/products/{id}")
    Optional<ProductTO> getProductById(@PathVariable Long id);

}
