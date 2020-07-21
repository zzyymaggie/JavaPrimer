package xyz.zzyymaggie.spring.javaconfig.service.impl;

import xyz.zzyymaggie.spring.javaconfig.service.BookService;
import xyz.zzyymaggie.spring.javaconfig.service.DependencyService;

public class BookServiceImpl implements BookService {
    public BookServiceImpl(DependencyService dependencyService) {

    }
}
