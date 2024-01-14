package com.matidominati.shoppingcartservice.shoppingcartservice.mapper;

import com.matidominati.shoppingcartservice.shoppingcartservice.model.CartEntity;
import com.matidominati.shoppingcartservice.shoppingcartservice.model.CartTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartMapper {

    CartTO map(CartEntity cartEntity);
}
