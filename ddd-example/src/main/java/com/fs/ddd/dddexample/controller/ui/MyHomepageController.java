/*
 * MyHomepageController.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.ddd.dddexample.controller.ui;

import com.fs.ddd.dddexample.app.BlogDisplayService;
import com.fs.ddd.dddexample.controller.data.UserHomepageVO;
import com.fs.ddd.dddexample.domain.blog.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import  com.fs.ddd.dddexample.app.UserHomepageService;
import java.util.List;

/**
 * @author fangzhang
 *
 */
@RestController
@RequestMapping("/api/fangzhang/homepage")
public class MyHomepageController {
    @Autowired
    private BlogDisplayService mBlogService;

    @Autowired
    private UserHomepageService mUserService;

    @GetMapping("/blog")
    public List<com.fs.ddd.controller.data.MyBlogVO> getMyBlogList() {
        Long userId = null;
       return mBlogService.getMyBlogList(userId);
    }

    @GetMapping("/user")
    public UserHomepageVO getUserHomePage() {
        Long userId = null;
        UserHomepageVO homepageVO = mUserService.getUser(userId);
        return homepageVO;
    }
}
