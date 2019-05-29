/*
 * UserDao.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.dddold.dao;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author fangzhang
 *
 */
@Component
public class UserDao {
    public List<UserPO> getUserByUserIds(List<Long> userId) {
        return null;
    }

    public UserPO getUserByUserId(Long userId) {
        return null;
    }
}
