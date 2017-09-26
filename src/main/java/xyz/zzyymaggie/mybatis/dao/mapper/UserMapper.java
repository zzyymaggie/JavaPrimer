/*
 * @(#)UserMapper.java
 *
 * Copyright (c) 1999-2017 7thOnline, Inc.
 * 24 W 40th Street, 11th Floor, New York, NY 10018, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of 7thOnline,
 * Inc. ("Confidential Information").  You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with 7thOnline.
 */
package xyz.zzyymaggie.mybatis.dao.mapper;

import java.util.List;

import xyz.zzyymaggie.mybatis.model.User;

public interface UserMapper {
    public List<User> selectUser();
    
    public void insert(User user);
}
