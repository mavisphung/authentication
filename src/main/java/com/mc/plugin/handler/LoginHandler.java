package com.mc.plugin.handler;

import com.mc.plugin.Plugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;

public class LoginHandler implements Listener, CommandExecutor {
    private Plugin plugin;
    private boolean notLoggedIn = true;
    private FileConfiguration config;

    public LoginHandler(Plugin plugin) {
        this.plugin = plugin;
        config = plugin.getConfig();
        plugin.getCommand("login").setExecutor(this);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent ev) {
        if (notLoggedIn) handlePreLogin(ev.getPlayer(), ev);
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent ev) {
        if (notLoggedIn) handlePreLogin(ev.getPlayer(), ev);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent ev) {
        if (notLoggedIn) handlePreLogin(ev.getPlayer(), ev);
    }

    public void handlePreLogin(Player player, Event ev) {
        if (player != null && ev instanceof Cancellable) {
            ((Cancellable) ev).setCancelled(true);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent ev) {
        notLoggedIn = true;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            p.sendMessage(translateToColor("&aYou have logged in!"));
            notLoggedIn = false;
        }
        return true;
    }

    public String translateToColor(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
