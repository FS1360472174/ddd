/*
 * MyHomepageController.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.dddold.controller.ui;

import com.fs.dddold.controller.data.BlogVO;
import com.fs.dddold.controller.data.MyBlogVO;
import com.fs.dddold.controller.data.UserHomepageVO;
import com.fs.dddold.service.BlogService;
import com.fs.dddold.service.UserService;
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
@RequestMapping("/ddd/old/my/homepage")
public class MyHomepageController {
    @Autowired
    private BlogService mBlogService;

    @Autowired
    private UserService mUserService;

    @GetMapping
    public List<MyBlogVO> getMyBlogList() {
        Long userId = null;
        return mBlogService.getMyBlogList(userId);
    }

    @GetMapping
    public UserHomepageVO getUserHomePage() {
        Long userId = null;
        UserHomepageVO homepageVO = mUserService.getUser(userId);
        return homepageVO;
    }
}
