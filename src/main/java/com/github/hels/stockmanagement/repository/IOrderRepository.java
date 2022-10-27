package com.github.hels.stockmanagement.repository;

import com.github.hels.stockmanagement.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long > {
}
