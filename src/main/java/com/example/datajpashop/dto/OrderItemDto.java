package com.example.datajpashop.dto;

import com.example.datajpashop.domain.OrderItem;
import lombok.Data;

@Data
public class OrderItemDto {
    private int orderPrice;
    private int count;
    private String itemName;

    public OrderItemDto(OrderItem orderItem)
    {
        orderPrice=orderItem.getOrderPrice();
        count=orderItem.getCount();
        itemName=orderItem.getItem().getName();
    }
}
