/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.msm595.NoDrop;

import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerMoveEvent;
/**
 *
 * @author Alex
 */
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
