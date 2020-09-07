package com.twu.dao;

import com.google.common.collect.Maps;
import com.twu.config.ConfigCommand;
import com.twu.constant.Constant;
import com.twu.domain.HotSearch;
import com.twu.domain.User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

@Component
public class HotSearchDao {

    private static TreeMap<Integer, HotSearch> data = Maps.newTreeMap();

    public TreeMap<Integer, HotSearch> check() {
        return data;
    }

    /**
     * 添加热搜事件
     */
    public void add(HotSearch hotSearch) {
        data.put(hotSearch.getRank(), hotSearch);
    }

    /**
     * 投票热搜事件
     */
    public void vote(int rank, int ticketNum, User user) {
        HotSearch hotSearch = data.get(rank);
        if (Objects.isNull(hotSearch)) {
            return;
        }
        int currTicket;
        // 判断是否为超级热搜
        if (hotSearch.getIsSuper() == Constant.Supper_Search) {
            currTicket = (ticketNum * 2) + hotSearch.getHeat();
        } else {
            currTicket = ticketNum + hotSearch.getHeat();
        }
        hotSearch.setHeat(currTicket);

        HotSearch temp = null;
        for (Map.Entry<Integer, HotSearch> map : data.entrySet()) {
            if (map.getKey() < rank && map.getValue().getHeat() < currTicket) {
                temp = map.getValue();
                break;
            }
        }
        if (Objects.isNull(temp)) {
            return;
        }
        int startRank = temp.getRank();
        int endRank = hotSearch.getRank();
        user.setTicketNum(user.getTicketNum() - ticketNum);
        hotSearch.setRank(startRank);
        data.put(startRank, hotSearch);
        // 重新排序
        for (int i = startRank + 1; i <= endRank; i++) {
            HotSearch anTemp = data.get(i);
            temp.setRank(i);
            data.put(i, temp);
            temp = anTemp;
        }
    }

    /**
     * 购买热搜事件
     */
    public void buy(int rank, int money, int buyRank) {
        HotSearch hotSearch = data.get(rank);
        if (Objects.isNull(hotSearch)) {
            return;
        }
        int currMoney = hotSearch.getMoney() + money;

        HotSearch temp = null;
        if (data.get(buyRank).getMoney() < currMoney) {
            temp = data.get(buyRank);
        }
        if (Objects.isNull(temp)) {
            System.out.println(Constant.Buy_Fail_Message);
            return;
        }
        hotSearch.setMoney(currMoney);
        int startRank = temp.getRank();
        int endRank = hotSearch.getRank();
        hotSearch.setRank(startRank);
        data.put(startRank, hotSearch);
        data.remove(endRank);
        System.out.println(Constant.Buy_Success_Message);
    }

    /**
     * 添加超级热搜事件
     */
    public void superAdd(int rank) {
        HotSearch hotSearch = data.get(rank);
        hotSearch.setIsSuper(Constant.Supper_Search);
    }

    /**
     * 获取当前排名
     */
    public int getCurrRank() {
        if (data.isEmpty()) {
            return 1;
        }
        return data.lastKey() + 1;
    }

    /**
     * 根据热搜名获取排名
     */
    public int getRankByName(String name) {
        for (Map.Entry<Integer, HotSearch> map : data.entrySet()) {
            if (map.getValue().getName().equals(name)) {
                return map.getKey();
            }
        }
        return -1;
    }
}
