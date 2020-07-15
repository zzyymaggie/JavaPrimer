package xyz.zzyymaggie.spring.aop2.source.service;

import xyz.zzyymaggie.spring.aop2.source.model.Order;

// OrderService.java
public interface OrderService {

    Order createOrder(String username, String product);

    Order queryOrder(String username);
}
