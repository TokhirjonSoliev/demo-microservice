package com.example.demo3.impl;

import com.example.demo3.dto.InventoryResponse;
import com.example.demo3.dto.OrderLineItemsDto;
import com.example.demo3.dto.OrderRequest;
import com.example.demo3.dto.OrderResponseDto;
import com.example.demo3.mapper.OrderMapper;
import com.example.demo3.model.Order;
import com.example.demo3.model.OrderLineItems;
import com.example.demo3.repository.OrderRepository;
import com.example.demo3.service.OrderService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final WebClient webClient;

    @Override
    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::orderToOrderResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDto getOrderById(String id) {
        Order order = orderRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new NotFoundException("order not found"));
        return orderMapper.orderToOrderResponseDto(order);
    }

    @Override
    public OrderResponseDto createOrder(OrderRequest orderRequest) {
        Order order = orderMapper.createNewOrder();

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(orderMapper::orderLineItemsDtoToOrderLineItems)
                .collect(Collectors.toList());
        order.setOrderLineItemsList(orderLineItems);
        Order saveOrder = orderRepository.save(order);

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        // call inventory service
        InventoryResponse[] inventoryResponses = webClient.get()
                .uri("http://localhost:8084/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allMatch = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);
        if (allMatch){
            return orderMapper.orderToOrderResponseDto(saveOrder);
        }
        else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }
    }

    @Override
    public OrderResponseDto editOrder(String id, OrderRequest orderRequest) {
        Order order = orderRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new NotFoundException("order not found"));

        List<OrderLineItems> orderLineItemsList = order.getOrderLineItemsList()
                .stream()
                .map(orderLineItems -> {
                    for (OrderLineItemsDto orderLineItemsDto : orderRequest.getOrderLineItemsDtoList()) {
                        if (orderLineItemsDto.getId().equals(orderLineItems.getId())) {
                            orderMapper.orderLineItemsDtoToOrderLineItems(orderLineItemsDto, orderLineItems);
                            break;
                        }
                    }
                    return orderLineItems;
                }).collect(Collectors.toList());
        order.setOrderLineItemsList(orderLineItemsList);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.orderToOrderResponseDto(savedOrder);
    }

    @Override
    public String deleteOrder(String id) {
        Order order = orderRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new NotFoundException("order not found"));
        orderRepository.delete(order);
        return "order deleted";
    }
}
