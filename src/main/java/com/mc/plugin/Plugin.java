package com.mc.plugin;

import com.mc.plugin.handler.LoginHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Plugin extends JavaPlugin {

    LoginHandler loginHandler;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "Authen is running...");
        this.loginHandler = new LoginHandler(this);
        this.getServer().getPluginManager().registerEvents(loginHandler, this);
//        this.getConfig().options().copyDefaults(true);
//        saveConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
