package me.ryans1230.rickroll;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class RickRollCommand extends Command {
    private RickRoll plugin;
    private List<String> toggled = new ArrayList<>();
    private Map<UUID, Long> rollCooldown = new HashMap<>();
    private Map<UUID, Long> rollRecent = new HashMap<>();

    RickRollCommand(RickRoll plugin) {
        super("rickroll", "rickroll.use", "rr");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&c[Rick Astley] You must be a online to use this command!")));
        } else {
            ProxiedPlayer s = (ProxiedPlayer) sender;
            if (args.length == 0) {
                help(s);
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("toggle")) {
                    if (sender.hasPermission("rickroll.toggle")) toggleRickRolls(s);
                } else {
                    ProxiedPlayer target;
                    target = plugin.getProxy().getPlayer(args[0]);
                    if (target != null) {
                        processRickRoll(s, target);
                    } else {
                        help(s);
                    }
                }
            } else {
                help(s);
            }
        }
    }

    private void processRickRoll(ProxiedPlayer s, ProxiedPlayer target) {
        if (s.equals(target)) {
            //Can't rick roll yourself, skrub
            s.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&c[Rick Astley] You cannot rick roll yourself!")));
        } else if (rollCooldown.containsKey(s.getUniqueId()) && rollCooldown.get(s.getUniqueId()) > System.currentTimeMillis()) {
            //Easy there, it's not a race!
            s.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&c[Rick Astley] You may only use this command once every 30 minutes!")));
        } else if (rollRecent.containsKey(target.getUniqueId()) && rollRecent.get(target.getUniqueId()) > System.currentTimeMillis()) {
            //They probably know its a rick roll already
            s.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&c[Rick Astley] " + target.getDisplayName() + " was rick rolled too recently, please try again later.")));
        } else if (target.hasPermission("rickroll.exempt")) {
            //Probably an owner who gave themselves this permission to get out of it
            s.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&c[Rick Astley] Unable to rick roll " + target.getDisplayName())));
        } else {
            //Get the video randomly
            String video = getVideo();
            //Let the sender know we are delivering the video
            s.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', plugin.senderMessage)));
            //RICK ROLL THE SKRUB!
            target.sendMessage(new ComponentBuilder("").create());
            TextComponent rickroll = new TextComponent(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', plugin.receiverMessage.replace("{sender}", s.getDisplayName()).replace("{video}", video))));
            rickroll.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.YELLOW + "Click here to watch: " + ChatColor.GREEN + video).create()));
            rickroll.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.youtube.com/watch?v=oHg5SJYRHA0"));
            target.sendMessage(rickroll);
            target.sendMessage(new ComponentBuilder("").create());
            //Cooldowns, if enabled
            if (plugin.senderCooldown > 0)
                rollCooldown.put(s.getUniqueId(), System.currentTimeMillis() + (plugin.senderCooldown * 1000));
            if (plugin.receiverCooldown > 0)
                rollRecent.put(target.getUniqueId(), System.currentTimeMillis() + (plugin.receiverCooldown * 1000));
        }
    }

    private void toggleRickRolls(ProxiedPlayer sender) {
        String username = sender.getName();
        if(toggled.contains(username)) {
            toggled.remove(username);
            sender.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&6[Rick Astley] You have been removed from the toggle list")));
        } else {
            toggled.add(sender.getName());
            sender.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&6[Rick Astley] You have temporarily toggled rick rolls")));
            int random = plugin.getRandomInt(24);
            plugin.getProxy().getScheduler().schedule(plugin, () -> {
                if(toggled.contains(username)) toggled.remove(username);
            }, random, TimeUnit.HOURS);
        }
    }

    private void help(ProxiedPlayer sender) {
        sender.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&cUsage: /rickroll <player> | Sends a rick roll to an online player\n")));
        if(sender.hasPermission("rickroll.toggle")) {
            sender.sendMessage(TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', "&cUsage: /rickroll toggle | Toggle your ability to receive rick rolls for a random amount of time\n")));
        }
    }
    private String getVideo() {return plugin.videos.get(plugin.getRandomInt(plugin.videos.size()));}
}