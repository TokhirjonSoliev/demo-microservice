package com.example.demo3.repository;

import com.example.demo3.model.Order;
import com.example.demo3.model.OrderLineItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
