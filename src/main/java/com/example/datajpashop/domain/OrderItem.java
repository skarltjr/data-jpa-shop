package com.example.datajpashop.domain;

import com.example.datajpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    //생성매서드
    public static OrderItem createOrderItem(int price, int count, Item item) {
        OrderItem orderItem= new OrderItem();
        orderItem.setCount(count);
        orderItem.setItem(item);
        orderItem.setOrderPrice(price);
        item.removeStock(count);
        return orderItem;
    }

    public void cancel() {
        getItem().addStock(count);
    }

    //==조회로직==//
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }

}
