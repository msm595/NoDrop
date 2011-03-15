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

import java.util.ArrayList;
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
        
        //remove them from inventory if they don't drop
//        if(!plugin.drops("helmet"))
//            inv.setHelmet(null);
//        if(!plugin.drops("chestplate"))
//            inv.setChestplate(null);
//        if(!plugin.drops("leggings"))
//            inv.setLeggings(null);
//        if(!plugin.drops("boots"))
//            inv.setBoots(null);
//        
//        if(!plugin.drops("held item"))
//            inv.remove(heldS);
//        
//        for(ItemStack i : contents) {
//            if(i!=null&&!plugin.drops(""+i.getTypeId()))
//                inv.remove(i);
//        }
    }
    
    public void restore(Player player) {
        restore(player.getInventory());
        player.updateInventory();
    }
    
    public ArrayList<ItemStack> getArmor() {
        ArrayList<ItemStack> i = new ArrayList<ItemStack>();
        i.add(helmet);
        i.add(chestplate);
        i.add(leggings);
        i.add(boots);
        i.add(held);
        return i;
    }
    
    public void restore(PlayerInventory inv) {
        
        if(boots!=null && boots.getTypeId()!=0) {
            if((plugin.keeps("equipped armor") && !plugin.drops("boots"))
                    || plugin.keeps(""+boots.getTypeId()))
                inv.setBoots(boots);
        }
        

        if(chestplate!=null && chestplate.getTypeId()!=0) {
            if((plugin.keeps("equipped armor") && !plugin.drops("chestplate"))
                    || plugin.keeps(""+chestplate.getTypeId()))
                inv.setChestplate(chestplate);
        }

        if(leggings!=null && leggings.getTypeId()!=0) {
            if((plugin.keeps("equipped armor") && !plugin.drops("leggings"))
                    || plugin.keeps(""+leggings.getTypeId()))
                inv.setLeggings(leggings);
        }

        if(helmet!=null && helmet.getTypeId()!=0) {
            if((plugin.keeps("equipped armor") && !plugin.drops("helmet"))
                    || plugin.keeps(""+helmet.getTypeId()))
                inv.setHelmet(helmet);
        }
        
        
        
        for(int i=0;i<contents.length;i++) {
            if(contents[i]!=null&&!plugin.keeps(""+contents[i].getTypeId()))
                contents[i].setTypeId(0);
        }
        
        if(plugin.keeps("held item") || plugin.keeps(""+held.getTypeId()))
            contents[heldS]=held;
        
        inv.setContents(contents);
    }
}
