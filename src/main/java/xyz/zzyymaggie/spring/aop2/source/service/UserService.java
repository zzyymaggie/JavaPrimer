package xyz.zzyymaggie.spring.aop2.source.service;

import xyz.zzyymaggie.spring.aop2.source.model.User;

// UserService.java
public interface UserService {

    User createUser(String firstName, String lastName, int age);

    User queryUser();
}
