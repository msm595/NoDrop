package com.msm595.NoDrop;

import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;


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
        
        ArrayList<ItemStack> armor = plugin.armors(player); //get a list of the armor of the player
        
        for(int i=0;i<event.getDrops().size();i++) {
            if(event.getDrops().get(i) != null //the items is actually an item
                    && !plugin.drops(""+event.getDrops().get(i).getTypeId())
                    && !armor.contains(event.getDrops().get(i))) { //deal with armor seperately
                event.getDrops().remove(i);
                i--;
            }
        }
        
        if(!plugin.drops("equipped armor") && !plugin.drops("helmet"))
            event.getDrops().remove(armor.get(0));
        if(!plugin.drops("equipped armor") && !plugin.drops("chestplate"))
            event.getDrops().remove(armor.get(1));
        if(!plugin.drops("equipped armor") && !plugin.drops("leggings"))
            event.getDrops().remove(armor.get(2));
        if(!plugin.drops("equipped armor") && !plugin.drops("boots"))
            event.getDrops().remove(armor.get(3));
        if(!plugin.drops("held item"))
            event.getDrops().remove(armor.get(4));
    }
}
