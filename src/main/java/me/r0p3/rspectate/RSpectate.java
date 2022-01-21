package me.r0p3.rspectate;

import me.r0p3.rspectate.commands.SpectateToggle;
import org.bukkit.plugin.java.JavaPlugin;

public final class RSpectate extends JavaPlugin
{

    @Override
    public void onEnable()
    {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getCommand("rspectate").setExecutor(new SpectateToggle());
        FileManager.getConfig().options().copyDefaults(true);
        FileManager.save();
    }
}
