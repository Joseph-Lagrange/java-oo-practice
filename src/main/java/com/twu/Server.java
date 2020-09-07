package com.twu;

import com.google.common.collect.Lists;
import com.twu.config.ConfigCommand;
import com.twu.constant.Constant;
import com.twu.domain.User;
import com.twu.manager.CommandManager;
import com.twu.scanner.Invoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
class Server {

    @Autowired
    private Invoker invoker;

    void start() {
        System.out.println(Constant.Welcome_Message);
        List<ConfigCommand> commands = CommandManager.getLoginCommands();
        for (ConfigCommand command : commands) {
            System.out.println(command.getModuleId() + "\t" + command.getDescription());
        }
        int moudleId;
        String username;
        String password;
        User user;
        while (true) {
            Scanner console = new Scanner(System.in);
            moudleId = console.nextInt();

            switch (moudleId) {
                case Constant.User_Command:
                    System.out.println(Constant.User_Login_Message);
                    console.nextLine();
                    username = console.nextLine();
                    user = User.create(username);
                    System.out.printf(Constant.User_Command_Choose, username);
                    requestOperation(commands, moudleId, user);
                    break;

                case Constant.Admin_Command:
                    System.out.println(Constant.User_Login_Message);
                    console.nextLine();
                    username = console.nextLine();
                    System.out.println(Constant.Admin_Login_Message);
                    password = console.nextLine();
                    user = User.volidAdmin(username, password);
                    if (Objects.isNull(user)) {
                        System.out.println(Constant.Admin_Vaild_Fail_Message);
                        reLogin(commands, username);
                        break;
                    }
                    requestOperation(commands, moudleId, user);
                    break;
            }
            if (moudleId == Constant.Quit_Command) {
                break;
            }
        }
    }

    /**
     * 循环请求操作
     */
    private void requestOperation(List<ConfigCommand> commands, int moudleId, User user) {
        while (true) {
            listCommands(moudleId);
            Scanner console = new Scanner(System.in);
            int cmd = console.nextInt();
            if ((moudleId == Constant.User_Command && cmd == Constant.User_Quit_Command) ||
                    (moudleId == Constant.Admin_Command && cmd == Constant.Admin_Quit_Command)) {
                reLogin(commands, user.getUsername());
                break;
            }
            sendRequest(moudleId, cmd, user);
        }
    }

    /**
     * 列出可执行命令
     */
    private void listCommands(int moudleId) {
        TreeMap<Integer, ConfigCommand> commandMap = CommandManager.getCommands(moudleId);
        for (Map.Entry<Integer, ConfigCommand> map : commandMap.entrySet()) {
            if (map.getKey() == 0) {
                continue;
            }
            System.out.println(map.getKey() + "\t" + map.getValue().getDescription());
        }
    }

    /**
     * 重新登录
     */
    private void reLogin(List<ConfigCommand> commands, String username) {
        System.out.printf(Constant.Quit_Message, username);
        System.out.println(Constant.Relogin_Message);
        for (ConfigCommand command : commands) {
            System.out.println(command.getModuleId() + "\t" + command.getDescription());
        }
    }

    /**
     * 发送请求
     */
    private void sendRequest(int moudleId, int cmd, User user) {
        invoker.invoke(moudleId, cmd, user);
    }
}
