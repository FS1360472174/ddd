/*
 * UserHomepageVO.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.dddold.controller.data;

import lombok.Builder;
import lombok.Data;

/**
 * @author fangzhang
 *
 */
@Data
public class UserHomepageVO {
    private Long userId;

    private String name;

    private String pic;

    private String area;

    private String intro;

    private String company;

    private Integer blogCount;
}
