package com.twu.domain;

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
