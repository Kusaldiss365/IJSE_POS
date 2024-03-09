package com.pos.posapi.util.mapper;


import com.pos.posapi.dto.ItemDto;
import com.pos.posapi.dto.responsedto.ResponseItemDto;
import com.pos.posapi.enity.Item;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item toItem(ItemDto itemDto1);

    List<ItemDto> toItemDtoList(List<Item> items);

    ItemDto toItemDto(Item item);

    List<ResponseItemDto> toResponseItemDtoList(List<Item> item);
}
