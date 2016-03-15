package uk.adzwoolly.mc.bombdefusal;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

public class Bomb extends BukkitRunnable{
	
	private BombManager bombManager;
	private Character name;
	private Block block;
	private int time;
	public String defuseCode = UUID.randomUUID().toString();
	
	ArmorStand as;
	
	public Bomb(BombManager bombManager, Character name, Block block, int time){
		this.bombManager = bombManager;
		this.name = name;
		this.block = block;
		this.time = time;
		
		Location blockLoc = block.getLocation();
		Location centreLoc = new Location(blockLoc.getWorld(), blockLoc.getBlockX() + 0.5, blockLoc.getBlockY() + 0.5, blockLoc.getBlockZ() - 0.5);
		
		as = block.getWorld().spawn(centreLoc, ArmorStand.class);
		as.setGravity(false);
		as.setVisible(false);
		as.setCustomName("Bomb " + this.name + ": " + this.time + " seconds");
		as.setCustomNameVisible(true);
		
		Bukkit.broadcastMessage(BombDefusalMain.msgPrefix + "Bomb " + this.name + " has been armed!");
	}
	
	public void defuse(){
		if(time <= 0){
			Bukkit.broadcastMessage(BombDefusalMain.msgPrefix + "Bomb " + name + " has " + ChatColor.RED + "detonated!");
		} else{
			Bukkit.broadcastMessage(BombDefusalMain.msgPrefix + "Bomb " + name + " has been " + ChatColor.GREEN + "defused!");
		}
		as.remove();
		this.cancel();
	}
	
	public String getDefuseCode(){
		return defuseCode;
	}
	
	@Override
	public void run() {
		as.setCustomName("Bomb " + this.name + ": " + this.time + " seconds");
		
		if(time %10 == 0){
			defuseCode = UUID.randomUUID().toString().substring(0, 6);
			Bukkit.broadcastMessage(BombDefusalMain.msgPrefix + "Bomb " + name + "'s defuse code is now '" + defuseCode + "'.");
		}
		
		if(time %10 == 0 || (time <= 10 && time %2 == 0) || time <= 5){
			Bukkit.broadcastMessage(BombDefusalMain.msgPrefix + time + " seconds remaining on bomb " + name + "!");
		}
		
		if(time <= 0){
			block.setType(Material.AIR);
			block.getWorld().createExplosion(block.getLocation(), 100.0f);
			bombManager.removeBomb(this);
			this.cancel();
		}
		time--;
	}
	
}
