/*
 * BlogService.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.mvc.service;

import com.fs.mvc.controller.data.BlogDetailVO;
import com.fs.mvc.controller.data.BlogVO;
import com.fs.mvc.controller.data.MyBlogVO;
import com.fs.mvc.dao.BlogDao;
import com.fs.mvc.dao.BlogDisplayUserBO;
import com.fs.mvc.dao.BlogPO;
import com.fs.mvc.dao.BlogUserBO;
import com.fs.mvc.dao.UserDao;
import com.fs.mvc.dao.UserPO;
import com.fs.mvc.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fangzhang
 *
 */
@Service
public class BlogService {
    @Autowired
    private BlogDao mBlogDao;

    @Autowired
    private UserDao mUserDao;

    public List<MyBlogVO> getMyBlogList(Long userId) {
        // 获取我的博客
        List<BlogPO> blogs = mBlogDao.getMyBlog(userId);
        if (CollectionUtils.isEmpty(blogs)) {
            return Collections.emptyList();
        }
        List<Long> userIds = blogs.stream().map(p -> p.getAuthorId()).collect(Collectors.toList());
        // 获取用户信息
        List<UserPO> userPOS = mUserDao.getUserByUserIds(userIds);
        // TODO
        return Collections.emptyList();
    }

    public BlogDetailVO getBlogDetail(Long id) {
        BlogUserBO blogUserBO = mBlogDao.getBlogById(id);
        // TODO
        // 数据parse
        return null;

    }

    public PageResult<BlogVO> getBlogList(int start, int num) {
        PageResult<BlogDisplayUserBO> blogDisplayUserBO = mBlogDao.pageGetBlog(start, num);
        // 数据转换
        // TODO
        return null;
    }


}
