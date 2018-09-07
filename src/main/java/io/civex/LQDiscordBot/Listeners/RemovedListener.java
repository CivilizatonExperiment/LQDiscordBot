package io.civex.LQDiscordBot.Listeners;

import io.civex.LQDiscordBot.LQDiscordBot;
import io.civex.LoginSystem.Events.Enums.EventType;
import io.civex.LoginSystem.Events.LoginQueueEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by Ryan on 9/6/2018.
 */
public class RemovedListener implements Listener
{
    private LQDiscordBot plugin;

    public RemovedListener(LQDiscordBot plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void missedQueue(LoginQueueEvent event)
    {
        if (event.getEventType().equals(EventType.REMOVEFROMQUEUE))
        {
            String message = event.getPlayerName() + " has missed their login window.";
            plugin.sendMessageAsync(message);
        }
    }
}