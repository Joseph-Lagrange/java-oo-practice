package com.twu.domain;

import com.twu.constant.Constant;

/**
 * 角色
 */
public class User {

    private String username;

    private String password;

    // 用户所拥有的投票数
    private int ticketNum;

    // 权限：0-用户;1-管理员;
    private int authority;

    /**
     * 创建普通用户
     */
    public static User create(String username) {
        User user = new User();
        user.username = username;
        user.ticketNum = Constant.Default_Ticket_Num;
        user.authority = Constant.User_Authority;
        return user;
    }

    /**
     * 校验管理员身份
     */
    public static User volidAdmin(String username, String password) {
        if (!password.equals(Constant.Admin_Password)) {
            return null;
        }
        User user = new User();
        user.username = username;
        user.password = password;
        user.ticketNum = Constant.Default_Ticket_Num;
        user.authority = Constant.Admin_Authority;
        return user;
    }

    /**
     * 校验当前用户是否为管理员
     */
    public static boolean checkAdmin(User user) {
        return user.getAuthority() == Constant.Admin_Authority;
    }

    // Getter & Setter

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(int ticketNum) {
        this.ticketNum = ticketNum;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }
}
