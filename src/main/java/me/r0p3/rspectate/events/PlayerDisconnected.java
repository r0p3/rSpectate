package me.r0p3.rspectate.events;

import me.r0p3.rspectate.FileManager;
import me.r0p3.rspectate.PlayerSpectateManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerDisconnected implements Listener
{
    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent e)
    {
        if(FileManager.getConfig().get(e.getPlayer().getUniqueId().toString()) != null && FileManager.getConfig().getInt(e.getPlayer().getUniqueId() + ".Timer") != 0)
            PlayerSpectateManager.endSpectate(e.getPlayer(), "");
    }
}
