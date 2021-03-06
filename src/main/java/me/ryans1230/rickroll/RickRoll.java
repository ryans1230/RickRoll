package me.ryans1230.rickroll;

import net.md_5.bungee.api.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class RickRoll extends Plugin {
    List<String> videos = new ArrayList<>();
    int senderCooldown;
    int receiverCooldown;
    String receiverMessage;
    String senderMessage;
    String prefix;

    @Override
    public void onEnable() {
        ConfigUtil util = new ConfigUtil(this);
        util.createRickRoll();
        getLogger().info("Loading RickRoll v" + getDescription().getVersion() + ". . . .");
        getLogger().info("Developed by: ryans1230");
        getProxy().getPluginManager().registerCommand(this, new RickRollCommand(this));
        loadConfig();
    }

    private void loadConfig() {
            //Load the settings
        videos = ConfigUtil.c.getStringList("videos");
        prefix = ConfigUtil.c.getString("prefix");
        senderCooldown = ConfigUtil.c.getInt("sender-cooldown");
        receiverCooldown = ConfigUtil.c.getInt("receiver-cooldown");
        senderMessage = ConfigUtil.c.getString("sender-message");
        receiverMessage = ConfigUtil.c.getString("receiver-message");
            //Data checks
        if(!senderMessage.contains("{receiver}") && !senderMessage.contains("{video}")) {
            getLogger().severe("Configuration option `sender-message` does not contain \"{receiver}\" or \"{video}\". Please fix before attempting to use this plugin!");
            failedStart();
        }
        if(!receiverMessage.contains("{sender}") && !receiverMessage.contains("{video}")) {
            getLogger().severe("Configuration option `receiver-message` does not contain \"{sender}\" or \"{video}\". Please fix before attempting to use this plugin!");
            failedStart();
        }
        //if(prefix.contains("{message}")) {
            //getLogger().severe("Configuration option `prefix` does not contain \"{message}\". Please fix before attempting to use this plugin!");
            //failedStart();
        //}
        if(videos.size() < 5) {
            getLogger().warning("Your video collection contains less than 5 videos. It is recommended to have at least 10 videos in the list. Consider adding more in the config.yml");
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling RickRoll. . . .");
    }

    int getRandomInt(int i) {
        Random r = new Random();
        return r.nextInt(i);
    }

    private void failedStart() {
        getProxy().getPluginManager().unregisterCommands(this);
        this.onDisable();
    }
}