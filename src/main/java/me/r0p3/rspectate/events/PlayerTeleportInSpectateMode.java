package me.r0p3.rspectate.events;

import me.r0p3.rspectate.PlayerMessages;
import me.r0p3.rspectate.PlayerSpectateManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class PlayerTeleportInSpectateMode implements Listener
{
    @EventHandler
    public void teleport(PlayerTeleportEvent e)
    {
        Player player = e.getPlayer();
        
        if(e.getCause() == PlayerTeleportEvent.TeleportCause.SPECTATE && PlayerSpectateManager.isSpectating(player))
        {
            if(!player.hasPermission("rspectate.spectateteleport"))
            {
                e.setCancelled(true);
                PlayerMessages.sendCustomMessage(player, ChatColor.RED + "You don't have the required permission to do this");
            }
        }
    }
}
