package me.r0p3.rspectate.commands;

import me.r0p3.rspectate.FileManager;
import me.r0p3.rspectate.PlayerMessages;
import me.r0p3.rspectate.PlayerSpectateManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpectateToggle implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;
            if (args.length == 0)
            {

                if (player.hasPermission("rspectate.spectate"))
                {
                    if (FileManager.getConfig().getConfigurationSection(player.getUniqueId().toString()) == null)
                    {
                        PlayerSpectateManager.startSpectate(player, "");
                    }
                    else
                    {
                        PlayerSpectateManager.endSpectate(player, "");
                    }

                    FileManager.save();
                }
            }
            else if (args.length == 1)
            {
                Player targetPlayer = Bukkit.getPlayer(args[0]);
                if (targetPlayer != null)
                {
                    if (player.hasPermission("rspectate.spectatetoggleother"))
                    {
                        if (FileManager.getConfig().get(targetPlayer.getUniqueId().toString()) == null)
                            PlayerSpectateManager.startSpectate(targetPlayer, "");
                        else
                            PlayerSpectateManager.endSpectate(targetPlayer, "");
                    }
                    else
                        player.sendMessage(ChatColor.RED + "You don't have the required permission to do this");

                }
                else
                    PlayerMessages.sendCustomMessage(player, ChatColor.RED + args[0] + " is not online!");
            }
        }
        else
        {
            if (args.length == 1)
            {
                Player targetPlayer = Bukkit.getPlayer(args[0]);
                if (targetPlayer != null)
                {
                    if (FileManager.getConfig().get(targetPlayer.getUniqueId().toString()) == null)
                    {
                        PlayerSpectateManager.startSpectate(targetPlayer, ChatColor.GREEN + "Spectate mode was turned on by server");
                        Bukkit.getLogger().info("Spectate mode was turned on for player " + targetPlayer.getName());
                    }
                    else
                    {
                        PlayerSpectateManager.endSpectate(targetPlayer, ChatColor.RED + "Spectate mode was turned off by server");
                        Bukkit.getLogger().info("Spectate mode was turned off for player " + targetPlayer.getName());
                    }
                }
                else
                    Bukkit.getLogger().info("Player " + args[0] + " could not be found");
            }
        }
        return true;
    }
}
