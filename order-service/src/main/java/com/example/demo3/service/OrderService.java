package com.example.demo3.service;

import com.example.demo3.dto.OrderRequest;
import com.example.demo3.dto.OrderResponseDto;

import java.util.List;

public interface OrderService {
    List<OrderResponseDto> getAllOrders();

    OrderResponseDto getOrderById(String id);

    OrderResponseDto createOrder(OrderRequest orderRequest);

    OrderResponseDto editOrder(String id, OrderRequest orderRequest);

    String deleteOrder(String id);
}
