/*
 * UserHomepageService.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.ddd.dddexample.app;

import com.fs.ddd.dddexample.controller.data.UserHomepageVO;
import com.fs.ddd.dddexample.dao.UserDao;
import com.fs.ddd.dddexample.dao.UserPO;
import com.fs.ddd.dddexample.domain.blog.OpenBlogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cnstonefang@gmail.com
 */
@Service
public class UserHomepageService {
    @Autowired
    private UserDao mUserDao;

    @Autowired
    private OpenBlogService mOpenBlogService;

    public UserHomepageVO getUser(Long userId) {
        UserPO userPO = mUserDao.getUserByUserId(userId);
        Integer blogCount = mOpenBlogService.getUserBlogCount(userId);
        UserHomepageVO userHomepageVO = new UserHomepageVO();
        BeanUtils.copyProperties(userPO, userHomepageVO);
        userHomepageVO.setBlogCount(blogCount);
        return userHomepageVO;
    }
}
