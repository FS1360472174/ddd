/*
 * OpenBlogService.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.ddd.domain;

import com.fs.ddd.util.PageResult;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fangzhang
 *
 */
public interface OpenBlogService {

    List<BlogDTO> getMyBlogList(Long userId) ;

    BlogDTO getBlogDetail(Long id);

    PageResult<BlogDTO> getBlogList(int start, int num);
}
