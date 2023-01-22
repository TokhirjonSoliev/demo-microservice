package com.example.demo3.mapper;

import com.example.demo3.dto.OrderLineItemsDto;
import com.example.demo3.dto.OrderResponseDto;
import com.example.demo3.model.Order;
import com.example.demo3.model.OrderLineItems;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.UUID;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {
    OrderLineItems orderLineItemsDtoToOrderLineItems(OrderLineItemsDto orderLineItemsDto);
    void orderLineItemsDtoToOrderLineItems(OrderLineItemsDto orderLineItemsDto, @MappingTarget OrderLineItems orderLineItems);

    OrderResponseDto orderToOrderResponseDto(Order order);

    default Order createNewOrder() {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        return order;
    }
}
