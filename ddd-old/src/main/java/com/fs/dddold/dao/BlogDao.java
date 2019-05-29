/*
 * BlogDao.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.dddold.dao;

import com.fs.dddold.util.PageResult;
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

    public BlogUserBO getBlogById(Long id) {
        // 数据库left join 两张表
        return null;
    }

    public PageResult<BlogDisplayUserBO> pageGetBlog(int start, int num) {
        // 数据库left join
        // 和博客详情页数据不同，定义不同结构
        return null;
    }
}
