package io.civex.LQDiscordBot.Listeners;

import io.civex.LQDiscordBot.LQDiscordBot;
import io.civex.LoginSystem.Events.Enums.EventType;
import io.civex.LoginSystem.Events.LoginQueueEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 * Created by Ryan on 9/6/2018.
 */
public class JoinedQueueListener implements Listener
{
    private LQDiscordBot plugin;

    public JoinedQueueListener(LQDiscordBot plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void JoinedQueueListener(LoginQueueEvent event)
    {
        if (event.getEventType().equals(EventType.JOINQUEUE))
        {
            int qPos = 0;
            int qSize = 0;
            String message = event.getPlayerName() + " has joined the queue and is ";

            if (event.getQueue().containsKey(event.getPlayerUUID()))
            {
                qPos = event.getQueue().get(event.getPlayerUUID());
            }

            if (event.getQueue().size() > 0)
            {
                qSize = event.getQueue().size();
            }

            if (qPos == 1)
            {
                message += "first ";
            }
            else
            {
                message += "last ";

            }

            message += "in the queue.";

            if (qSize > 1)
            {
                message += "\nThe queue currently has " + qSize + " people in it.";
            }

            plugin.sendMessageAsync(message);
        }
    }
}