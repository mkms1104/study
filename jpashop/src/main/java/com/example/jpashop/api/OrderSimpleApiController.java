package com.example.jpashop.api;

import com.example.jpashop.domain.Order;
import com.example.jpashop.repository.OrderRepository;
import com.example.jpashop.repository.order.simpleQuery.OrderSimpleQueryDto;
import com.example.jpashop.repository.order.simpleQuery.OrderSimpleQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {
    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;

    @GetMapping("/api/v3/simple-orders")
    public List<OrderSimpleQueryDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        return orders.stream().map(o -> new OrderSimpleQueryDto(o.getId(), o.getMember().getName(), o.getOrderDate(), o.getStatus(), o.getMember().getAddress())).collect(Collectors.toList());
    }

    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4() {
        return orderSimpleQueryRepository.findOrderDtos();
    }
}
