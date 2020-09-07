package com.twu.manager;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.twu.config.ConfigCommand;
import com.twu.constant.Constant;
import jxl.Sheet;
import jxl.Workbook;

import java.io.InputStream;
import java.util.*;

/**
 * 命令配置类
 */
public class CommandManager {

    // key:1-用户行为;2-管理员行为;3-退出;
    private static HashMap<Integer, TreeMap<Integer, ConfigCommand>> commandContainer;

    /**
     * 获取命令列表
     */
    public static TreeMap<Integer, ConfigCommand> getCommands(int moduleId) {
        return commandContainer.getOrDefault(moduleId, Maps.newTreeMap());
    }

    /**
     * 获取命令
     */
    public static ConfigCommand getCommand(int moduleId, int cmd) {
        if (commandContainer.containsKey(moduleId)) {
            return commandContainer.get(moduleId).getOrDefault(cmd, ConfigCommand.create(moduleId, cmd));
        }
        return ConfigCommand.create(moduleId, cmd);
    }

    /**
     * 获取登录命令
     */
    public static List<ConfigCommand> getLoginCommands() {
        ArrayList<ConfigCommand> list = Lists.newArrayList();
        list.add(commandContainer.get(Constant.User_Command).get(0));
        list.add(commandContainer.get(Constant.Admin_Command).get(0));
        list.add(commandContainer.get(Constant.Quit_Command).get(0));
        return list;
    }

    /**
     * 初始化命令配置类容器
     */
    public static void init(String containerName) {
        Workbook workbook = null;
        HashMap<Integer, TreeMap<Integer, ConfigCommand>> container = null;
        InputStream inputStream;
        try {
            inputStream = CommandManager.class.getClassLoader().getResourceAsStream("config/container/CommandConfigExcel.xls");
            // 获得工作簿对象
            workbook = Workbook.getWorkbook(inputStream);
            // 获得所有工作表
            Sheet[] sheets = workbook.getSheets();
            // 遍历工作表
            if (sheets != null && sheets.length != 0) {
                for (Sheet sheet : sheets) {
                    // 获取Sheet名称
                    String sheetName = sheet.getName();
                    // 获得行数
                    int rows = sheet.getRows();
                    // 获得列数 int cols = sheet.getColumns();
                    if (sheetName.equals(containerName)) {
                        // 从Excel加载Map
                        container = new HashMap<>();
                        // 读取数据
                        for (int row = 1; row < rows; row++) {
                            ConfigCommand command = new ConfigCommand();
                            command.setModuleId(Integer.parseInt(sheet.getCell(0, row).getContents()));
                            command.setCmd(Integer.parseInt(sheet.getCell(1, row).getContents()));
                            command.setDescription(sheet.getCell(2, row).getContents());
                            command.setMethod(sheet.getCell(3, row).getContents());
                            if (!container.containsKey(command.getModuleId())) {
                                container.put(command.getModuleId(), Maps.newTreeMap());
                            }
                            container.get(command.getModuleId()).put(command.getCmd(), command);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
        commandContainer = container;
    }
}
