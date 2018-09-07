package io.civex.LQDiscordBot.Listeners;

import io.civex.LQDiscordBot.LQDiscordBot;
import io.civex.LoginSystem.Events.Enums.EventType;
import io.civex.LoginSystem.Events.LoginQueueEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

/**
 * Created by Ryan on 9/6/2018.
 */
public class OnTheClockListener implements Listener
{
    private LQDiscordBot plugin;

    public OnTheClockListener(LQDiscordBot plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onTheClockEvent(LoginQueueEvent event)
    {
        if (event.getEventType().equals(EventType.ONCLOCK))
        {
            String message = event.getPlayerName() + " is now on the clock.";

            if (event.getOnTheClock().size() > 1)
            {
                message += "\nAs well as: ";

                for (UUID uuid : event.getOnTheClock())
                {
                    if (uuid != event.getPlayerUUID())
                    {
                        message += " [" + event.getUUIDtoName().get(uuid) + "]";
                    }
                }
            }

            plugin.sendMessageAsync(message);
        }
    }
}