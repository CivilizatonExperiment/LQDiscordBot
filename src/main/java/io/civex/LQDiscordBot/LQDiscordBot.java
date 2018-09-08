package io.civex.LQDiscordBot;

import io.civex.LQDiscordBot.Listeners.JoinedQueueListener;
import io.civex.LQDiscordBot.Listeners.LoginListener;
import io.civex.LQDiscordBot.Listeners.OnTheClockListener;
import io.civex.LQDiscordBot.Listeners.RemovedListener;
import io.civex.LQDiscordBot.Utils.SendToDiscord;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;


import java.util.logging.Level;

/**
 * Created by Ryan on 9/5/2018.
 */
public class LQDiscordBot extends JavaPlugin
{
    public static FileConfiguration config;
    private String discordInfo;

    private boolean joinedQueueToggle;
    private boolean loggedInToggle;
    private boolean onTheClockToggle;
    private boolean missedQueueToggle;


    @Override
    public void onEnable()
    {
        saveDefaultConfig();
        reloadConfig();

        discordInfo = config.getString("discord-info", "");
        joinedQueueToggle = config.getBoolean("joined-queue-message", false);
        loggedInToggle = config.getBoolean("logged-in-message", false);
        onTheClockToggle = config.getBoolean("on-the-clock-message", false);
        missedQueueToggle = config.getBoolean("missed-queue-message", false);

        if (joinedQueueToggle) getServer().getPluginManager().registerEvents(new JoinedQueueListener(this), this);
        if (loggedInToggle) getServer().getPluginManager().registerEvents(new LoginListener(this), this);
        if (onTheClockToggle) getServer().getPluginManager().registerEvents(new OnTheClockListener(this), this);
        if (missedQueueToggle) getServer().getPluginManager().registerEvents(new RemovedListener(this), this);

        getLogger().log(Level.INFO, "joined queue message [" + joinedQueueToggle + "]");
        getLogger().log(Level.INFO, "logged in message [" + loggedInToggle + "]");
        getLogger().log(Level.INFO, "on the clock message [" + onTheClockToggle + "]");
        getLogger().log(Level.INFO, "missed queue message [" + missedQueueToggle + "]");
    }

    @Override
    public void onDisable()
    {

    }

    public void loadConfig()
    {
        config = Bukkit.getPluginManager().getPlugin("LQDiscordBot").getConfig();
    }

    @Override
    public void reloadConfig()
    {
        super.reloadConfig();
        loadConfig();
    }

    public void sendMessageAsync(String message)
    {
        if (discordInfo.equals("https://discordapp.com/api/webhooks/") || discordInfo.equals(""))
        {
            getServer().getLogger().log(Level.SEVERE, "You've not set discord info in the config.");
            getServer().getLogger().log(Level.SEVERE, "No message sent.");
            return;
        }

        BukkitScheduler scheduler = getServer().getScheduler();
        scheduler.runTaskAsynchronously(this, new SendToDiscord(this, message, discordInfo));
    }
}