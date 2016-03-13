package uk.adzwoolly.mc.bombdefusal;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

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
			e.setCancelled(bombManager.isArmed(block));
		}
	}

}
