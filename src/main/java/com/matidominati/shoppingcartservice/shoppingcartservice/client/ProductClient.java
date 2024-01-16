package com.matidominati.shoppingcartservice.shoppingcartservice.client;

import com.matidominati.shoppingcartservice.shoppingcartservice.client.model.ProductTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "product-service", url = "http://localhost:8080")
public interface ProductClient {

    //    @GetMapping("/products/customize/{id}")
//    Optional<ProductTO> getProductById(@PathVariable Long id);
    @PostMapping("/products/customize/{baseProductId}")
    ProductTO customize(@PathVariable Long baseProductId,
                        @RequestParam(required = false) List<String> selectedConfigurations,
                        @RequestParam(required = false) List<String> selectedAccessories);

}



