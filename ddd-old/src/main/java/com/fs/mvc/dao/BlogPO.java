/*
 * BlogPO.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.mvc.dao;

import lombok.Data;

/**
 * @author fangzhang
 *
 */
@Data
public class BlogPO {
    private Long id;
    private Long authorId;
    private String title;
    private String pic;
    private String text;
    private String tagIds;
}
