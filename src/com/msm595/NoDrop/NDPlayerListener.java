package com.msm595.NoDrop;

import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerMoveEvent;


public class NDPlayerListener extends PlayerListener{
    NoDrop plugin;
    
    public NDPlayerListener(NoDrop p) {
        plugin = p;
    }
    
    @Override
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        plugin.setCanDrop(event.getPlayer());
    }
    
    @Override
    public void onPlayerMove(PlayerMoveEvent event) {
        plugin.getDrop(event.getPlayer());
    }
}
