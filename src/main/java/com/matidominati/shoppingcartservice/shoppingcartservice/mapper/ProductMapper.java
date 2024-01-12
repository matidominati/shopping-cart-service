package com.matidominati.shoppingcartservice.shoppingcartservice.mapper;

import com.matidominati.shoppingcartservice.shoppingcartservice.client.model.ProductTO;
import com.matidominati.shoppingcartservice.shoppingcartservice.model.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    CartItem map(ProductTO productTO);
}
