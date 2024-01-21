package com.matidominati.shoppingcartservice.shoppingcartservice.client;

import com.matidominati.shoppingcartservice.shoppingcartservice.client.model.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "product-service", url = "${product.service.api.url}")
public interface ProductClient {
    @PostMapping("/products/customize/{baseProductId}")
    ProductDto customize(@PathVariable Long baseProductId,
                         @RequestParam(required = false) List<Long> selectedConfigurationIds,
                         @RequestParam(required = false) List<Long> selectedAccessoryIds);

}



