package me.r0p3.rspectate;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class PlayerMessages
{
    public static void sendMessage(Player player, String configIndex)
    {
        try
        {
            Plugin plugin = RSpectate.getPlugin(RSpectate.class);
            plugin.reloadConfig();
            player.sendMessage(convertColor(plugin.getConfig().getString("Prefix") + " " + convertColor(plugin.getConfig().getString(configIndex))));
        }
        catch (Exception e)
        {
            Bukkit.getLogger().info(e.getMessage());
        }
    }

    private static String convertColor(String text)
    {
        if(text.length() == 0)
            return text;
        text = text.replace("&0", ChatColor.BLACK + "");
        text = text.replace("&1", ChatColor.DARK_BLUE + "");
        text = text.replace("&2", ChatColor.DARK_GREEN + "");
        text = text.replace("&3", ChatColor.DARK_AQUA + "");
        text = text.replace("&4", ChatColor.DARK_RED + "");
        text = text.replace("&5", ChatColor.DARK_PURPLE + "");
        text = text.replace("&6", ChatColor.GOLD + "");
        text = text.replace("&7", ChatColor.GRAY + "");
        text = text.replace("&8", ChatColor.DARK_GRAY + "");
        text = text.replace("&9", ChatColor.BLUE + "");
        text = text.replace("&a", ChatColor.GREEN + "");
        text = text.replace("&b", ChatColor.AQUA + "");
        text = text.replace("&c", ChatColor.RED + "");
        text = text.replace("&d", ChatColor.LIGHT_PURPLE + "");
        text = text.replace("&e", ChatColor.YELLOW + "");
        text = text.replace("&f", ChatColor.WHITE + "");
        text = text.replace("&k", ChatColor.MAGIC + "");
        text = text.replace("&l", ChatColor.BOLD + "");
        text = text.replace("&m", ChatColor.STRIKETHROUGH + "");
        text = text.replace("&n", ChatColor.UNDERLINE + "");
        text = text.replace("&o", ChatColor.ITALIC + "");
        text = text.replace("&r", ChatColor.RESET + "");
        return  text;
    }
}
