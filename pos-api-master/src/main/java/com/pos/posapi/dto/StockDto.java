package com.pos.posapi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class StockDto {
    private String stock_id;

    private String name;

    private int quantity;

    public StockDto(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
}
