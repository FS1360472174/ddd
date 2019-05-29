/*
 * UserService.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.dddold.service;

import com.fs.dddold.controller.data.UserHomepageVO;
import com.fs.dddold.dao.BlogDao;
import com.fs.dddold.dao.UserDao;
import com.fs.dddold.dao.UserPO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fangzhang
 *
 */
@Service
public class UserService {
    @Autowired
    private UserDao mUserDao;

    @Autowired
    private BlogDao mBlogDao;

    public UserHomepageVO getUser(Long userId) {
        UserPO userPO = mUserDao.getUserByUserId(userId);
        Integer blogCount = mBlogDao.countUserBlog(userId);
        UserHomepageVO userHomepageVO = new UserHomepageVO();
        BeanUtils.copyProperties(userPO, userHomepageVO);
        userHomepageVO.setBlogCount(blogCount);
        return userHomepageVO;
    }
}

