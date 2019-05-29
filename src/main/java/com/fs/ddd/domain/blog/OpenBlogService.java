/*
 * OpenBlogService.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.ddd.domain.blog;

import com.fs.ddd.util.PageResult;

import java.util.List;

/**
 * @author fangzhang
 *
 */
public interface OpenBlogService {

    List<BlogDTO> getMyBlogList(Long userId) ;

    BlogDTO getBlogDetail(Long id);

    PageResult<BlogDTO> getBlogList(int start, int num);
}
