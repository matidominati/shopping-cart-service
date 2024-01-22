package com.matidominati.shoppingcartservice.shoppingcartservice.mapper;

import com.matidominati.shoppingcartservice.shoppingcartservice.model.entity.CartEntity;
import com.matidominati.shoppingcartservice.shoppingcartservice.model.dto.CartDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartMapper {

    CartDto map(CartEntity cartEntity);
}
