/*
 * BlogDetailController.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.ddd.dddexample.controller.ui;

import com.fs.ddd.dddexample.app.BlogDisplayService;
import com.fs.ddd.dddexample.controller.data.BlogDetailVO;
import com.fs.ddd.dddexample.domain.blog.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fangzhang
 *
 */
@RestController
@RequestMapping("/api/fangzhang/blog/detail")
public class BlogDetailController {
    @Autowired
    private BlogDisplayService mBlogService;

    @GetMapping
    public BlogDetailVO getBlogdDetail(@PathVariable(value = "id") long id) {
       return mBlogService.getBlogDetail(id);
    }
}
