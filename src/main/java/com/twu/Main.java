package com.twu;

import com.twu.constant.Constant;
import com.twu.manager.CommandManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Server server = applicationContext.getBean(Server.class);
        // 加载配置文件
        CommandManager.init(Constant.CommandConfig_Init);
        server.start();
    }
}
