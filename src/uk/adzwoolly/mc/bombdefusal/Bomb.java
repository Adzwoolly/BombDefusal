package uk.adzwoolly.mc.bombdefusal;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

public class Bomb extends BukkitRunnable{
	
	BombManager bombManager;
	Character name;
	Block block;
	int time;
	
	public Bomb(BombManager bombManager, Character name, Block block, int time){
		this.bombManager = bombManager;
		this.name = name;
		this.block = block;
		this.time = time;
		Bukkit.broadcastMessage(BombDefusalMain.msgPrefix + "Bomb " + this.name + " has been armed!");
	}
	
	public void defuse(){
		this.cancel();
	}
	
	@Override
	public void run() {
		if(time %10 == 0 || time <= 10){
			Bukkit.broadcastMessage(BombDefusalMain.msgPrefix + time + " seconds remaining on bomb " + name + "!");
		}
		
		if(time <= 0){
			Bukkit.broadcastMessage("BOOM!!!");
			block.setType(Material.AIR);
			block.getWorld().createExplosion(block.getLocation(), 0.0f);
			bombManager.removeBomb(this);
			this.cancel();
		}
		time--;
	}
	
}
