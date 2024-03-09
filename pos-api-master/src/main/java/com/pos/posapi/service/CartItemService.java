package com.pos.posapi.service;

import com.pos.posapi.dto.CartItemDto;
import com.pos.posapi.dto.responsedto.ResponseCartItemDto;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;

import java.util.List;

public interface CartItemService {
    CommonResponseDTO addItemToCart(String itemId, String cartId, int quantity, String token);
    CommonResponseDTO deleteCartItem(String id);
    CartItemDto getCartItemById(String id);
    List<CartItemDto> getCartItems();
    List<ResponseCartItemDto> getAllCartItems();
}
