package com.example.datajpashop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    //연관관계 편의 매서드
    public void setMember(Member member) {
        this.member=member;
        member.getOrders().add(this);
    }
    public void addOrderItem(OrderItem orderItem)
    {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    public void setDelivery(Delivery delivery)
    {
        this.delivery=delivery;
        delivery.setOrder(this);
    }

    //생성매서드
    public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
        Order order=new Order();
        order.setDelivery(delivery);
        order.setMember(member);

        for (OrderItem orderItem : orderItems) {
            order.addOrderItem(orderItem);
        }
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.ORDER);
        return order;
    }
    //비즈니스 로직 주문취소
    public void cancel()
    {
        if(delivery.getStatus()==DeliveryStatus.COMP)
        {
            throw new IllegalStateException("이미 배송중");
        }
        this.setStatus(OrderStatus.CANCEL);
        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }


    /*
     * 전체 주문 가격 조회
     */
    public int getTotalPrice()
    {
        int totalPrice=0;
        for(OrderItem orderItem : orderItems)
        {
            totalPrice+=orderItem.getTotalPrice();
        }
        return totalPrice;
    }

}
