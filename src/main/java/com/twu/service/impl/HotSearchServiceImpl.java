package com.twu.service.impl;

import com.twu.config.ConfigCommand;
import com.twu.constant.Constant;
import com.twu.dao.HotSearchDao;
import com.twu.domain.HotSearch;
import com.twu.domain.User;
import com.twu.service.HotSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

@Component
public class HotSearchServiceImpl implements HotSearchService {

    @Autowired
    private HotSearchDao hotSearchDao;

    /**
     * 查看热搜事件
     */
    @Override
    public void check(User user) {
        TreeMap<Integer, HotSearch> data = hotSearchDao.check();
        if (data.isEmpty()) {
            System.out.println("No Hot Search Data Available");
            return;
        }
        for (Map.Entry<Integer, HotSearch> map : data.entrySet()) {
            System.out.println(map.getKey() + "\t" + map.getValue().getName() + "\t" + map.getValue().getHeat() + "\t" + map.getValue().getMoney());
        }
    }

    /**
     * 投票热搜事件
     */
    @Override
    public void vote(User user) {
        System.out.println(Constant.Vote_Message);
        Scanner console = new Scanner(System.in);
        String name = console.nextLine();
        int rank = hotSearchDao.getRankByName(name);
        if (rank == -1) {
            System.out.println("No Such Hot Search");
            return;
        }
        System.out.println(Constant.Vote_Tip_Message + "(" + user.getTicketNum() + " tickets left)");
        int ticketNum = console.nextInt();
        if (ticketNum > user.getTicketNum()) {
            System.out.println("Tickets not Enough, Vote Fail");
            return;
        }
        hotSearchDao.vote(rank, ticketNum, user);
        System.out.println(Constant.Vote_Success_Message);
    }

    /**
     * 购买热搜事件
     */
    @Override
    public void buy(User user) {
        System.out.println(Constant.Buy_Message);
        Scanner console = new Scanner(System.in);
        String name = console.nextLine();
        int rank = hotSearchDao.getRankByName(name);
        if (rank == -1) {
            System.out.println("No Such Hot Search");
            return;
        }
        System.out.println(Constant.Buy_Tip_Message);
        int money = console.nextInt();
        System.out.println(Constant.Buy_Rank_Tip_Message);
        int buyRank = console.nextInt();
        hotSearchDao.buy(rank, money, buyRank);
    }

    /**
     * 添加热搜事件
     */
    @Override
    public void add(User user) {
        System.out.println(Constant.Add_Search_Message);
        Scanner console = new Scanner(System.in);
        String name = console.nextLine();
        int rank = hotSearchDao.getCurrRank();
        HotSearch hotSearch = HotSearch.create(name, rank);
        hotSearchDao.add(hotSearch);
        System.out.println(Constant.Add_Success_Message);
    }

    /**
     * 添加超级热搜事件
     */
    @Override
    public void superAdd(User user) {
        if (!User.checkAdmin(user)) {
            System.out.println(Constant.Super_Add_Fail_Message);
        }
        System.out.println(Constant.Add_Search_Message);
        Scanner console = new Scanner(System.in);
        String name = console.nextLine();
        int rank = hotSearchDao.getRankByName(name);
        if (rank == -1) {
            System.out.println("No Such Hot Search");
            return;
        }
        hotSearchDao.superAdd(rank);
        System.out.println(Constant.Super_Add_Success_Message);
    }
}
