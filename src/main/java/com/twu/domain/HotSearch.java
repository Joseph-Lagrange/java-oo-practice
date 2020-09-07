package com.twu.domain;

public class HotSearch {

    // 排名
    private int rank;

    private String name;

    // 热度/票数
    private int heat;

    // 金额
    private int money;

    // 0-不是超级热搜;1-超级热搜;
    private int isSuper;

    public static HotSearch create(String name, int rank) {
        HotSearch hotSearch = new HotSearch();
        hotSearch.name = name;
        hotSearch.rank = rank;
        return hotSearch;
    }

    // Getter & Setter

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeat() {
        return heat;
    }

    public void setHeat(int heat) {
        this.heat = heat;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getIsSuper() {
        return isSuper;
    }

    public void setIsSuper(int isSuper) {
        this.isSuper = isSuper;
    }
}
