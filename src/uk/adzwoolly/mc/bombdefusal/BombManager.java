package uk.adzwoolly.mc.bombdefusal;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

public class BombManager {
	
	Plugin plugin;
	
	BombManager(Plugin plugin){
		this.plugin = plugin;
	}
	
	HashMap<Character, Bomb> bombsName = new HashMap<Character, Bomb>();
	HashMap<Block, Bomb> bombsBlock = new HashMap<Block, Bomb>();
	
	Character name = 'A';
	
	public void addBomb(Block block){
		Character nextName = name++;
		Bomb newBomb = addBomb(nextName, block, 30);
		@SuppressWarnings("unused")
		BukkitTask task = newBomb.runTaskTimer(plugin, 0, 20*1);
	}
	
	public boolean isArmed(String bombName){
		Bukkit.broadcastMessage(BombDefusalMain.msgPrefix + "You need to defuse the bomb!");
		return bombsName.containsKey(bombName);
	}
	
	public boolean isArmed(Block block){
		Bukkit.broadcastMessage(BombDefusalMain.msgPrefix + "You need to defuse the bomb!");
		return bombsBlock.containsKey(block);
	}
	
	
	
	public boolean defuseBomb(String bombName){
		if(bombsName.containsKey(bombName.charAt(0))){
			Bomb bomb = bombsName.get(bombName.charAt(0));
			return defuseBomb(bomb);
		}
		return false;
	}
	
	public boolean defuseBomb(Block block){
		if(bombsBlock.containsKey(block)){
			Bomb bomb = bombsBlock.get(block);
			return defuseBomb(bomb);
		}
		return false;
	}
	
	private boolean defuseBomb(Bomb bomb){
		//if(bombs.containsValue(bomb)){
			//Insert complex diffusion stuff here, maybe?
			removeBomb(bomb);
			return false;
		//}
		//return false;
	}
	
	private Bomb addBomb(Character name, Block block, int time){
		Bomb newBomb = new Bomb(this, name, block, time);
		bombsName.put(name, newBomb);
		bombsBlock.put(block, newBomb);
		return newBomb;
	}
	
	protected void removeBomb(Bomb bomb){
		bomb.defuse();
		bombsName.values().remove(bomb);
		bombsBlock.values().remove(bomb);
	}
	
}
