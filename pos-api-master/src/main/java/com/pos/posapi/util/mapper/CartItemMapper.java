package com.pos.posapi.util.mapper;

import com.pos.posapi.dto.CartItemDto;
import com.pos.posapi.enity.CartItem;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapper {
    CartItem toCartItem(CartItemDto cartItemDto);

    CartItemDto toCartItemDto(CartItem cartItem);

    List<CartItemDto> toCartItemDtoList(List<CartItem> cartItems);
}
