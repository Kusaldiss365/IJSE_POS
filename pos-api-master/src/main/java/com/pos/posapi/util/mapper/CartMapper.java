package com.pos.posapi.util.mapper;

import com.pos.posapi.dto.CartDto;
import com.pos.posapi.enity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    Cart toCart(CartDto cartDto);

    CartDto toCartDto(Cart cart);
}
