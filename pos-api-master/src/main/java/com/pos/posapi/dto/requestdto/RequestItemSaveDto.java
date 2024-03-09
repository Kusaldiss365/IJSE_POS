package com.pos.posapi.dto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RequestItemSaveDto {
    private String itemName;
    private int itemQuantity;
    private double itemUnitPrice;
    private String itemDescription;
    private boolean activeState;
}
