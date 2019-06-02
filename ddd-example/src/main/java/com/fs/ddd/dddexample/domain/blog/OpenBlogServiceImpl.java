/*
 * OpenBlogServiceImpl.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.ddd.dddexample.domain.blog;

/**
 * @author cnstonefang@gmail.com
 */

import com.fs.ddd.dddexample.dao.BlogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenBlogServiceImpl implements OpenBlogService {

    @Autowired
    private BlogDao mBlogDao;

    @Override
    public int getUserBlogCount(final Long userId) {
        return mBlogDao.countUserBlog(userId);
    }
}
