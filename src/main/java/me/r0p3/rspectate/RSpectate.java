package me.r0p3.rspectate;

import me.r0p3.rspectate.commands.SpectateToggle;
import me.r0p3.rspectate.events.PlayerDisconnected;
import me.r0p3.rspectate.events.PlayerTeleportInSpectateMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;

public final class RSpectate extends JavaPlugin
{

    @Override
    public void onEnable()
    {
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);

        updateConfig();

        getCommand("rspectate").setExecutor(new SpectateToggle());
        getServer().getPluginManager().registerEvents(new PlayerTeleportInSpectateMode(), this);
        getServer().getPluginManager().registerEvents(new PlayerDisconnected(), this);
        FileManager.getConfig().options().copyDefaults(true);
        FileManager.save();
    }

    private void updateConfig()
    {
        File fileOnDisk = new File(this.getDataFolder(), "config.yml");
        FileConfiguration fileOnDiskConfiguration = YamlConfiguration.loadConfiguration(fileOnDisk);

        for (String section : getConfig().getConfigurationSection("").getKeys(true))
        {
            if(fileOnDiskConfiguration.get(section) != null) continue;
            fileOnDiskConfiguration.set(section, getConfig().get(section));
        }
        updateComments(fileOnDiskConfiguration);
        try
        {
            fileOnDiskConfiguration.save(fileOnDisk);
        }
        catch (Exception e)
        {
            getServer().getLogger().warning("Error when trying to update config.yml");
        }
    }

    private void updateComments(FileConfiguration fileOnDiskConfiguration)
    {
        fileOnDiskConfiguration.setComments("Spectate.Timer", Arrays.asList("How many seconds a player can be in spectate mode, put 0 for unlimited time"));
    }

    @Override
    public void onDisable()
    {
        for(Player player : getServer().getOnlinePlayers())
        {
            if(FileManager.getConfig().getInt(player.getUniqueId().toString() + ".Timer") != 0)
            {
                PlayerSpectateManager.endSpectate(player, "");
                getLogger().info("STOP SPECTATE");
            }
        }
    }
}
