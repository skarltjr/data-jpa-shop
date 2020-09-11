package com.example.datajpashop.repository;

import com.example.datajpashop.domain.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderJpaRepository {
    @PersistenceContext
    private EntityManager em;

    public List<Order> findFetchJoin()
    {
        return em.createQuery("select distinct o from Order o"+
                " join fetch o.member m"+
                " join fetch o.delivery d"+
                " join fetch o.orderItems oi"+
                " join fetch oi.item i", Order.class).getResultList();
    }
}
