package com.pos.posapi.dto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class RequestCustomerSaveDto {
    private String customerName;
    private String customerAddress;
    private String customerMobile;
    private boolean activeState;
}
