/*
 * OpenUserService.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.ddd.domain.user;

import java.util.List;

/**
 * @author fangzhang
 *
 */
public interface OpenUserService {

    List<UserDTO> getUserByUserIds(List<Long> userId);

    UserDTO getUserByUserId(Long userId);
}
