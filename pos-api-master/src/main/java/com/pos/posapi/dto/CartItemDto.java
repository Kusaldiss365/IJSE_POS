package com.pos.posapi.dto;

import com.pos.posapi.enity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CartItemDto {
    private String cartItemId;
    private ItemDto item;
    private CartDto cart;
    private UserDTO user;
    private int quantity;

    public CartItemDto(ItemDto item, CartDto cart, UserDTO user,int quantity) {
        this.item = item;
        this.cart = cart;
        this.user = user;
        this.quantity = quantity;
    }
}
