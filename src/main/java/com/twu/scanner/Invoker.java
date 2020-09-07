package com.twu.scanner;

import com.twu.config.ConfigCommand;
import com.twu.domain.User;
import com.twu.manager.CommandManager;
import com.twu.service.HotSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@Component
public class Invoker {

    @Autowired
    private HotSearchService hotSearchService;

    public void invoke(int moudleId, int cmd, User user) {
        ConfigCommand command = CommandManager.getCommand(moudleId, cmd);
        Class<? extends HotSearchService> clazz = hotSearchService.getClass();
        try {
            Method method = clazz.getMethod(command.getMethod(), User.class);
            method.invoke(hotSearchService, user);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
