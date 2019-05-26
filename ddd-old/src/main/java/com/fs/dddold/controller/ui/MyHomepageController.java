/*
 * MyHomepageController.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.dddold.controller.ui;

import com.fs.dddold.controller.data.BlogVO;
import com.fs.dddold.service.BlogService;
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

    @GetMapping
    public List<BlogVO> getMyBlogList() {
        Long userId = null;
        return mBlogService.getMyBlogList(userId);
    }
}
