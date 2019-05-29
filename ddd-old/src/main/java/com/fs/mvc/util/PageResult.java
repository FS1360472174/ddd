/*
 * PageResult.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.mvc.util;

import java.util.List;

/**
 * @author fangzhang
 *
 */
public class PageResult<T> {
    private Integer total;
    private Boolean hasMore;
    private List<T> result;
}
