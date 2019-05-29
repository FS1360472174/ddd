/*
 * BlogService.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.ddd.dddexample.domain.blog;

import com.fs.ddd.dddexample.domain.user.OpenUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fangzhang
 *
 */
@Service
public class BlogService {
    @Autowired
    private OpenUserService mOpenUserService;


}
