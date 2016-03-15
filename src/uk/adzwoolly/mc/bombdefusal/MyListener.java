package uk.adzwoolly.mc.bombdefusal;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class MyListener implements Listener{
	
	BombManager bombManager;
	
	public MyListener(BombManager bombManager){
		this.bombManager = bombManager;
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e){
		Block block = e.getBlock();
		if(block.getType() == Material.TNT){
			bombManager.addBomb(block);
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e){
		Block block = e.getBlock();
		if(block.getType() == Material.TNT){
			if(bombManager.isArmed(block)){
				e.getPlayer().sendMessage(BombDefusalMain.msgPrefix + "You need to defuse the bomb! /defuse <defuse_code>");
				e.setCancelled(true);
			}
		}
	}
	
	boolean team = false;
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		e.getPlayer().getInventory().addItem(new ItemStack(Material.GOLD_SWORD, 1));
		
		ItemStack chestPlate = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
		ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS, 1);
		
		LeatherArmorMeta meta = (LeatherArmorMeta) chestPlate.getItemMeta();
		if(team == false){
			meta.setColor(Color.RED);
			team = true;
		} else{
			meta.setColor(Color.BLUE);
			team = false;
		}
		chestPlate.setItemMeta(meta);
		leggings.setItemMeta(meta);
		e.getPlayer().getInventory().setChestplate(chestPlate);
		e.getPlayer().getInventory().setLeggings(chestPlate);
	}

}
