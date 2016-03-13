package uk.adzwoolly.mc.bombdefusal.commnds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import uk.adzwoolly.mc.bombdefusal.BombManager;

public class DefuseCommand implements CommandExecutor{
	
	private BombManager bombManager;
	
	public DefuseCommand(BombManager bombManager){
		this.bombManager = bombManager;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		if(args.length == 1){
			bombManager.defuseBomb(args[0]);
			return true;
		}
		return false;
	}

}
