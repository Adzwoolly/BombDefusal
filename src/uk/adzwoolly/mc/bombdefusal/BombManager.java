package uk.adzwoolly.mc.bombdefusal;

import java.util.HashMap;
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
		Bomb newBomb = addBomb(nextName, block, 45);
		@SuppressWarnings("unused")
		BukkitTask task = newBomb.runTaskTimer(plugin, 0, 20*1);
	}
	
	public boolean isArmed(String bombName){
		if(getBomb(bombName) != null){
			return true;
		}
		return false;
	}
	
	public boolean isArmed(Block block){
		if(getBomb(block) != null){
			return true;
		}
		return false;
	}
	
	public String getBombDefuseCode(String bombName){
		Bomb bomb = getBomb(bombName);
		if(bomb != null)
			return bomb.getDefuseCode();
		return null;
	}
	
	private Bomb getBomb(String bombName){
		return bombsName.get(Character.toUpperCase(bombName.charAt(0)));
	}
	
	private Bomb getBomb(Block block){
		return bombsBlock.get(block);
	}
	
	public boolean defuseBomb(String bombName){
		return removeBomb(getBomb(bombName));
	}
	
	public boolean defuseBomb(Block block){
		return removeBomb(getBomb(block));
	}
	
	private Bomb addBomb(Character name, Block block, int time){
		Bomb newBomb = new Bomb(this, name, block, time);
		bombsName.put(name, newBomb);
		bombsBlock.put(block, newBomb);
		return newBomb;
	}
	
	protected boolean removeBomb(Bomb bomb){
		if(bomb != null){
			bomb.defuse();
			bombsName.values().remove(bomb);
			bombsBlock.values().remove(bomb);
			return true;
		}
		return false;
	}
	
}
