package com.pos.posapi.dto;

import com.pos.posapi.enity.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ItemDto {
    private String itemId;
    private String itemName;
    private int itemQuantity;
    private double itemUnitPrice;
    private String description;
    private boolean activeState;

    private CategoryDto category;
    private StockDto stock;
    private CartDto cart;

    public ItemDto(String itemName, int itemQuantity, double itemUnitPrice, String description, boolean activeState, CategoryDto category, StockDto stock) {
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemUnitPrice = itemUnitPrice;
        this.description = description;
        this.activeState = activeState;
        this.category = category;
        this.stock = stock;
    }

    public ItemDto(String itemId, String itemName, int itemQuantity, double itemUnitPrice) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemUnitPrice = itemUnitPrice;
    }

    public ItemDto(String itemId, String itemName, int itemQuantity, double itemUnitPrice, String description, boolean activeState, CategoryDto category, StockDto stock) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemQuantity = itemQuantity;
        this.itemUnitPrice = itemUnitPrice;
        this.description = description;
        this.activeState = activeState;
        this.category = category;
        this.stock = stock;
    }
}
