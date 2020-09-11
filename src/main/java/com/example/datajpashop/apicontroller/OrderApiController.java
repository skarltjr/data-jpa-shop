package com.example.datajpashop.apicontroller;

import com.example.datajpashop.domain.Order;
import com.example.datajpashop.dto.OrderDto;
import com.example.datajpashop.repository.OrderJpaRepository;
import com.example.datajpashop.repository.OrderRepository;
import com.example.datajpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;
    private final OrderJpaRepository orderJpaRepository;

    @GetMapping("/orders1")
    public List<OrderDto> findOrders()
    {
        List<Order> orders = orderJpaRepository.findFetchJoin();
        List<OrderDto> collect = orders.stream().map(o -> new OrderDto(o)).collect(Collectors.toList());
        return collect;
    }

    @GetMapping("/orders/paging")
    public Page<OrderDto> findPaged()
    {
        PageRequest request= PageRequest.of(0,3);
        Page<Order> paged = orderRepository.findPaged(request);
        Page<OrderDto> orders = paged.map(o -> new OrderDto(o));
        return orders;
    }
}
