/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.msm595.NoDrop;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.craftbukkit.inventory.CraftInventory;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
/**
 *
 * @author Alex
 */
public class NDInventory {
    private ItemStack[] contents;
    private ItemStack helmet;
    private ItemStack chestplate;
    private ItemStack leggings;
    private ItemStack boots;
    private ItemStack held;
    private int heldS;
    
    NoDrop plugin;
    
    public NDInventory(Player player, NoDrop p) {
        this(player.getInventory(), p);
    }
    
    public NDInventory(PlayerInventory inv, NoDrop p) {
        contents = inv.getContents().clone();
        helmet = inv.getHelmet();
        chestplate = inv.getChestplate();
        leggings = inv.getLeggings();
        boots = inv.getBoots();
        plugin = p;
        
        heldS=inv.getHeldItemSlot();
        held = contents[heldS];
        contents[heldS] = null;
        System.out.println(held.getType().name());
    }
    
    public void restore(Player player) {
        restore(player.getInventory());
        player.updateInventory();
    }
    
    public void restore(PlayerInventory inv) {
        
        if(plugin.keeps("boots")||plugin.keeps("equiped armor")
                || plugin.keeps(""+boots.getTypeId()))
            inv.setBoots(boots);

        if(plugin.keeps("chestplate")||plugin.keeps("equiped armor")
                || plugin.keeps(""+chestplate.getTypeId()))
            inv.setChestplate(chestplate);

        if(plugin.keeps("helmet")||plugin.keeps("equiped armor")
                || plugin.keeps(""+helmet.getTypeId()))
            inv.setHelmet(helmet);

        if(plugin.keeps("leggings")||plugin.keeps("equiped armor")
                || plugin.keeps(""+leggings.getTypeId()))
            inv.setLeggings(leggings);
        
        
        
        for(int i=0;i<contents.length;i++) {
            if(contents[i]!=null&&!plugin.keeps(""+contents[i].getTypeId()))
                contents[i]=null;
        }
        
        if(plugin.keeps("held item") || plugin.keeps(""+held.getTypeId()))
            contents[heldS]=held;
        
        inv.setContents(contents);
    }
}
