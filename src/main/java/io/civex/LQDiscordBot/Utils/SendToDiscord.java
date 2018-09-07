package io.civex.LQDiscordBot.Utils;

import com.google.gson.JsonObject;
import io.civex.LQDiscordBot.LQDiscordBot;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;

/**
 * Created by Ryan on 9/6/2018.
 */
public class SendToDiscord implements Runnable
{
    LQDiscordBot plugin;
    String target;
    String payload;

    public SendToDiscord(LQDiscordBot plugin, String payload, String URL)
    {
        this.plugin = plugin;
        this.payload = payload;
        this.target = URL;
    }

    @Override
    public void run()
    {
        try
        {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("content", payload);

            URL address = new URL(target);
            HttpURLConnection conn = null;

            conn = (HttpURLConnection) address.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:49.0) Gecko/20100101 Firefox/49.0");
            conn.setRequestProperty("Content-type", "application/json; charset=windows-1252");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            conn.setRequestProperty("Content-length", String.valueOf(jsonObject.toString().length()));
            DataOutputStream outputStream;
            try
            {
                outputStream = new DataOutputStream(conn.getOutputStream());
                outputStream.writeBytes(jsonObject.toString());
                outputStream.flush();
                outputStream.close();
            }
            catch (IOException ex)
            {
                plugin.getLogger().severe(ex.getMessage());
                plugin.getLogger().severe(ex.getStackTrace().toString());
            }

            //plugin.getLogger().info("Message [" + conn.getResponseMessage() + "] code [" + conn.getResponseCode() + "]");

            conn.disconnect();
        }
        catch (Exception e)
        {
            plugin.getLogger().log(Level.SEVERE, e.getMessage());
            plugin.getLogger().log(Level.SEVERE, e.getStackTrace().toString());
        }
    }
}
