/*
 * UserPO.java
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
public class UserPO {
    private Long id;
    private String name;
    private String intro;
    private String company;
    private String area;
}
