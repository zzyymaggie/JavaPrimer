package xyz.zzyymaggie.spring.javaconfig.service.impl;

import xyz.zzyymaggie.spring.javaconfig.service.DependencyService;
import xyz.zzyymaggie.spring.javaconfig.service.OtherService;

public class OtherServiceImpl implements OtherService{
    public OtherServiceImpl(DependencyService dependencyService) {

    }
}
