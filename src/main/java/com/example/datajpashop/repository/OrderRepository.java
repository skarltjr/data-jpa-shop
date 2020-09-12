package com.example.datajpashop.repository;


import com.example.datajpashop.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("select o from Order o"+
            " join fetch o.member"+
            " join fetch o.delivery"+
            " join fetch o.orderItems oi"+
             " join fetch oi.item i")
    List<Order> findOrderFetch();

  @Query(value = "select distinct o from Order o join fetch o.delivery join fetch o.member" +
            " join fetch o.orderItems oi join fetch oi.item",
            countQuery ="select count(o.id) from Order o")
    Page<Order> findPaged(Pageable pageable);
}
