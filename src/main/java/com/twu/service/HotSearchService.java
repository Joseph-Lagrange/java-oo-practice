package com.twu.service;

import com.twu.domain.User;

import java.util.List;

public interface HotSearchService {

    /**
     * 查看热搜事件
     */
    void check(User user);

    /**
     * 投票热搜事件
     */
    void vote(User user);

    /**
     * 购买热搜事件
     */
    void buy(User user);

    /**
     * 添加热搜事件
     */
    void add(User user);

    /**
     * 添加超级热搜事件
     */
    void superAdd(User user);
}
