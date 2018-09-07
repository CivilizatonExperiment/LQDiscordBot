package io.civex.LQDiscordBot.Listeners;

import io.civex.LQDiscordBot.LQDiscordBot;
import io.civex.LoginSystem.Events.Enums.EventType;
import io.civex.LoginSystem.Events.LoginQueueEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by Ryan on 9/6/2018.
 */
public class LoginListener implements Listener
{
    private LQDiscordBot plugin;

    public LoginListener(LQDiscordBot plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void LoggedIn(LoginQueueEvent event)
    {
        if (event.getEventType().equals(EventType.JOIN))
        {
            String message = event.getPlayerName() + " has logged into the server.";
            plugin.sendMessageAsync(message);
        }
    }
}