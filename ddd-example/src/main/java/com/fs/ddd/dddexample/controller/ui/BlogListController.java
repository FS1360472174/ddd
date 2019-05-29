/*
 * BlogListController.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.ddd.controller.ui;

import com.fs.mvc.controller.data.BlogVO;
import com.fs.mvc.service.BlogService;
import com.fs.mvc.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fangzhang
 *
 */
@RestController
@RequestMapping("/api/fangzhang/blog/list")
public class BlogListController {
    @Autowired
    private BlogService mBlogService;


    @GetMapping
    public PageResult<BlogVO> getBlogList(@RequestParam(value = "start") final Integer start,
            @RequestParam(value = "num") final Integer num) {
       return mBlogService.getBlogList(start, num);
    }
}
