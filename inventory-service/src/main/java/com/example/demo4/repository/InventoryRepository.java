package com.example.demo4.repository;

import com.example.demo4.model.Inventory;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends CassandraRepository<Inventory, Long> {
    @AllowFiltering
    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
