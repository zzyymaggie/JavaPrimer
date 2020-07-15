package xyz.zzyymaggie.spring.aop2.source.service.impl;

import xyz.zzyymaggie.spring.aop2.source.model.Order;
import xyz.zzyymaggie.spring.aop2.source.service.OrderService;

// OrderServiceImpl.java
public class OrderServiceImpl implements OrderService {

    @Override
    public Order createOrder(String username, String product) {
        Order order = new Order();
        order.setUsername(username);
        order.setProduct(product);
        return order;
    }

    @Override
    public Order queryOrder(String username) {
        Order order = new Order();
        order.setUsername("test");
        order.setProduct("test");
        return order;
    }
}
