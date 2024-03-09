package com.pos.posapi.util.mapper;

import com.pos.posapi.dto.StockDto;
import com.pos.posapi.enity.Stock;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockMapper {
    StockDto toStockDto(Stock stock);
    List<StockDto> toStockDtoList(List<Stock> stocks);
    Stock toStock(StockDto stockDto1);
}
