package com.example.jpashop.repository;

import com.example.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    // queryDSL 적용 예정
//    public List<Order> findAll(OrderSearch orderSearch) {
//        return em.createQuery("select o from Order o join o.member m", Order.class).getResultList();
//    }
}
