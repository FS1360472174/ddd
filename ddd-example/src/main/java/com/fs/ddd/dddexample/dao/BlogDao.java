/*
 * BlogDao.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.ddd.dddexample.dao;


import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author fangzhang
 *
 */
@Component
public class BlogDao {

    public int countUserBlog(Long userId) {
        return 0;
    }

    public List<BlogPO> getMyBlog(Long userId) {
      return null;
    }

    public List<BlogPO> getBlogList(int start, int num) {
        return  null;
    }

}
