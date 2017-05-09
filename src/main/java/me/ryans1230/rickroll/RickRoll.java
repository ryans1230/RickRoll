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

    @Override
    public void onEnable() {
        ConfigUtil util = new ConfigUtil(this);
        util.createRickRoll();
        getLogger().info("Loading RickRoll v" + getDescription().getVersion() + ". . . .");
        getProxy().getPluginManager().registerCommand(this, new RickRollCommand(this));
        loadConfig();
    }

    private void loadConfig() {
        videos = ConfigUtil.c.getStringList("videos");
        senderCooldown = ConfigUtil.c.getInt("sender-cooldown");
        receiverCooldown = ConfigUtil.c.getInt("receiver-cooldown");
        senderMessage = ConfigUtil.c.getString("sender-message");
        receiverMessage = ConfigUtil.c.getString("receiver-message");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling RickRoll. . . .");
    }

    int getRandomInt(int i) {
        Random r = new Random();
        return r.nextInt(i);
    }
}