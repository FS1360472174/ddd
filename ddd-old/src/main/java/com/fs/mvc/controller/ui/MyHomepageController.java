/*
 * MyHomepageController.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.mvc.controller.ui;

import com.fs.mvc.controller.data.MyBlogVO;
import com.fs.mvc.controller.data.UserHomepageVO;
import com.fs.mvc.service.BlogService;
import com.fs.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
/**
 * @author fangzhang
 *
 */
@RestController
@RequestMapping("/api/fangzhang/homepage")
public class MyHomepageController {
    @Autowired
    private BlogService mBlogService;

    @Autowired
    private UserService mUserService;

    @GetMapping("/blog")
    public List<MyBlogVO> getMyBlogList() {
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
