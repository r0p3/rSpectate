package me.r0p3.rspectate;


import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;


public class FileManager
{
    private static File file;
    private static FileConfiguration fileConfiguration;
    static void setup()
    {
        file = new File(RSpectate.getPlugin(RSpectate.class).getDataFolder(), "resetLocations.yml");
        if(!file.exists())
        {
            try
            {
                file.createNewFile();
            }
            catch (Exception e)
            {
                Bukkit.getLogger().info(e.getMessage());
            }
        }
        fileConfiguration = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getConfig()
    {
        if(fileConfiguration == null)
            setup();
        return fileConfiguration;
    }

    public static void save()
    {
        try
        {
            fileConfiguration.save(file);
        }
        catch (Exception e)
        {

        }
    }

    public static void reload()
    {
        setup();
    }
}
