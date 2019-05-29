/*
 * BlogDisplayService.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.ddd.dddexample.app;

import com.fs.ddd.controller.data.MyBlogVO;
import com.fs.ddd.dddexample.controller.data.BlogDetailVO;
import com.fs.ddd.dddexample.controller.data.BlogVO;
import com.fs.ddd.dddexample.dao.BlogDao;
import com.fs.ddd.dddexample.dao.BlogPO;
import com.fs.ddd.dddexample.domain.blog.BlogService;
import com.fs.ddd.dddexample.domain.user.OpenUserService;
import com.fs.ddd.dddexample.domain.user.UserDTO;
import com.fs.ddd.dddexample.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cnstonefang@gmail.com
 */
@Service
public class BlogDisplayService {

    @Autowired
    private OpenUserService mOpenUserService;

    @Autowired
    private BlogDao mBlogDao;

    public List<MyBlogVO> getMyBlogList(Long userId) {
        // 获取我的博客
        List<BlogPO> blogs = mBlogDao.getMyBlog(userId);
        if (CollectionUtils.isEmpty(blogs)) {
            return Collections.emptyList();
        }
        List<Long> userIds = blogs.stream().map(p -> p.getAuthorId()).collect(Collectors.toList());
        // 获取用户信息
        List<UserDTO> userInfo = mOpenUserService.getUserByUserIds(userIds);
        // TODO
        return Collections.emptyList();
    }

    public BlogDetailVO getBlogDetail(Long id) {

        // TODO
        // 数据parse
        return null;

    }

    public PageResult<BlogVO> getBlogList(int start, int num) {
        // 数据转换
        // TODO
        return null;
    }
}
