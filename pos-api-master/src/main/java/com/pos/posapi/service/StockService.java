package com.pos.posapi.service;

import com.pos.posapi.dto.StockDto;
import com.pos.posapi.dto.requestdto.RequestStockDto;
import com.pos.posapi.dto.responsedto.core.CommonResponseDTO;

import java.util.List;

public interface StockService {
    CommonResponseDTO createStock(RequestStockDto stockDto);

    StockDto getStockById(String id);

    CommonResponseDTO deleteStock(String id);

    List<StockDto> getStocks();

    CommonResponseDTO updateStock(String id,RequestStockDto stockDto);
}
