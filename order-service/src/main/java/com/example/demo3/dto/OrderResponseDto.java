package com.example.demo3.dto;


import com.example.demo3.model.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    private Long id;
    private String orderNumber;
    private List<OrderLineItems> orderLineItemsList;
}
