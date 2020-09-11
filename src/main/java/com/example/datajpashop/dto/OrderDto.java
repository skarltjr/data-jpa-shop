package com.example.datajpashop.dto;

import com.example.datajpashop.domain.Address;
import com.example.datajpashop.domain.Order;
import com.example.datajpashop.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderDto{
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private Address address;
    private List<OrderItemDto> orderItems;
    
    public OrderDto(Order order)
    {
        orderId=order.getId();
        name=order.getMember().getName();
        orderDate=order.getOrderDate();
        status=order.getStatus();
        address=order.getDelivery().getAddress();
        orderItems = order.getOrderItems().stream().map(orderItem-> new OrderItemDto(orderItem)).collect(Collectors.toList());
    }
}
