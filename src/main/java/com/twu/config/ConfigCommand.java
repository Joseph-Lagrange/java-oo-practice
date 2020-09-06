package com.twu.config;

/**
 * 命令
 */
public class ConfigCommand {

    private int moduleId;

    private int cmd;

    private String description;

    public static ConfigCommand create(int moduleId, int cmd) {
        ConfigCommand command = new ConfigCommand();
        command.moduleId = moduleId;
        command.cmd = cmd;
        return command;
    }

    // Getter & Setter

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
