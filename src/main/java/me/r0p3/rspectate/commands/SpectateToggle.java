package me.r0p3.rspectate.commands;

import me.r0p3.rspectate.FileManager;
import me.r0p3.rspectate.PlayerSpectateManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpectateToggle implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(sender instanceof Player)
        {
            Player player = (Player)sender;

            if(FileManager.getConfig().getConfigurationSection(player.getUniqueId().toString()) == null)
            {
                PlayerSpectateManager.startSpectate(player);
            }
            else
            {
                PlayerSpectateManager.endSpectate(player);
            }
            FileManager.save();
        }
        return true;
    }
}
