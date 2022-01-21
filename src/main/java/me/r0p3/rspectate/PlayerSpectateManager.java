package me.r0p3.rspectate;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PlayerSpectateManager
{
    public static void startSpectate(Player player, String message)
    {
        try
        {
            String playerID = player.getUniqueId().toString();
            Location location = player.getLocation();
            Plugin plugin = RSpectate.getPlugin(RSpectate.class);
            plugin.reloadConfig();

            FileManager.getConfig().createSection(playerID);
            FileManager.getConfig().set(playerID + ".X", location.getX());
            FileManager.getConfig().set(playerID + ".Y", location.getY());
            FileManager.getConfig().set(playerID + ".Z", location.getZ());
            FileManager.getConfig().set(playerID + ".Yaw", location.getYaw());
            FileManager.getConfig().set(playerID + ".Pitch", location.getPitch());
            FileManager.getConfig().set(playerID + ".World", location.getWorld().getName());
            FileManager.getConfig().set(playerID + ".Gamemode", player.getGameMode().toString());
            FileManager.getConfig().set(playerID + ".Timer", plugin.getConfig().getInt("Spectate.Timer"));
            player.setGameMode(GameMode.SPECTATOR);

            FileManager.save();

            if(message == "")
                PlayerMessages.sendMessage(player, "Spectate.On_message");
            else
                PlayerMessages.sendCustomMessage(player, message);

            if(plugin.getConfig().getInt("Spectate.Timer") > 0)
            Bukkit.getScheduler().runTaskLater(plugin, new Runnable()
            {
                @Override
                public void run()
                {
                    endSpectate(player, "");
                }
            },(long) plugin.getConfig().getInt("Spectate.Timer") * 20);
        }
        catch (Exception e)
        {
            Bukkit.getLogger().info(e.getMessage());
        }
    }

    public static void endSpectate(Player player, String message)
    {
        String playerID = player.getUniqueId().toString();
        Location location;

        if(FileManager.getConfig().get(playerID) == null)
            return;

        location = new Location(
                Bukkit.getWorld(FileManager.getConfig().getString(playerID + ".World")),
                FileManager.getConfig().getDouble(playerID + ".X"),
                FileManager.getConfig().getDouble(playerID + ".Y"),
                FileManager.getConfig().getDouble(playerID + ".Z"),
                (float) FileManager.getConfig().getDouble(playerID + ".Yaw"),
                (float) FileManager.getConfig().getDouble(playerID + ".Pitch") );
        player.teleport(location);
        player.setGameMode(GameMode.valueOf(FileManager.getConfig().getString(playerID + ".Gamemode")));


        FileManager.getConfig().set(playerID, null);

        FileManager.save();
        if(message == "")
            PlayerMessages.sendMessage(player, "Spectate.Off_message");
        else
            PlayerMessages.sendCustomMessage(player, message);
    }

    public static boolean isSpectating(Player player)
    {
        return (FileManager.getConfig().get(player.getUniqueId().toString()) != null);
    }
}
