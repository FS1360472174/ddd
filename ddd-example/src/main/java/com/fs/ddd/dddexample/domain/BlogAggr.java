/*
 * BlogAggr.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.ddd.dddexample.domain;

import lombok.Data;

/**
 * @author fangzhang
 * 博客聚合根
 *
 */
@Data
public class BlogAggr {
    private Long id;
    private Long authorId;
    private String title;
    private String pic;
    private String text;


    public void save() {
        // save
    }

    public void updateState() {

    }
}
