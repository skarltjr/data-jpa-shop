package com.example.datajpashop.service;

import com.example.datajpashop.domain.Delivery;
import com.example.datajpashop.domain.Member;
import com.example.datajpashop.domain.Order;
import com.example.datajpashop.domain.OrderItem;
import com.example.datajpashop.domain.item.Item;
import com.example.datajpashop.repository.ItemRepository;
import com.example.datajpashop.repository.MemberRepository;
import com.example.datajpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    //주문
    @Transactional
    public Long order(Long memberId,Long itemId,int count)
    {
        Member member = memberRepository.findById(memberId).get();
        Item item=itemRepository.findById(itemId).get();

        Delivery delivery=new Delivery();
        delivery.setAddress(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item.getPrice(), count, item);

        Order order = Order.createOrder(member, delivery, orderItem);
        orderRepository.save(order);
        return order.getId();
    }

    //취소
    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        order.cancel();
    }

    public List<Order> findOrders()
    {
        return orderRepository.findAll();
    }
}
