package com.matidominati.shoppingcartservice.shoppingcartservice.mapper;

import com.matidominati.shoppingcartservice.shoppingcartservice.model.dto.CartItemDto;
import com.matidominati.shoppingcartservice.shoppingcartservice.model.entity.CartItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartItemMapper {

    CartItemDto map(CartItemEntity cartItemEntity);
}
