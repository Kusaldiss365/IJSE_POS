package com.pos.posapi.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ResponseCartItemDto {
    private String cartItemId;
    private int total;
    List<ResponseItemDto> itemDtos;
}
