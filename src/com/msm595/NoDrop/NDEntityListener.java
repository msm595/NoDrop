/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.msm595.NoDrop;

import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
/**
 *
 * @author Alex
 */
public class NDEntityListener extends EntityListener{
    NoDrop plugin;
    
    public NDEntityListener(NoDrop p) {
        plugin = p;
    }
    
    @Override
    public void onEntityDamage(EntityDamageEvent event) {
        if(!(event.getEntity() instanceof Player)) return;
        Player player = (Player)event.getEntity();
        
        if(player.getHealth()<1) return; //player already dead, will have no inventory
        
        
        if(plugin.noDrop(player) && player.getHealth()-event.getDamage()<1) {
             //player is about to die, store inventory
            
            plugin.addDrop(player);
        }
    }
    
    @Override
    public void onEntityDeath(EntityDeathEvent event) {
        if(!(event.getEntity() instanceof Player)) return;
        
        Player player = (Player)event.getEntity();        
        if(!plugin.noDrop(player)) return; //only if they are set to not drop
        
        while(!event.getDrops().isEmpty()) {
            event.getDrops().remove(0);
        }
    }
}
