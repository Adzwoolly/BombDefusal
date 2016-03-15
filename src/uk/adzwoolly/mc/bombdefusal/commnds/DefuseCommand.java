package uk.adzwoolly.mc.bombdefusal.commnds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import uk.adzwoolly.mc.bombdefusal.BombDefusalMain;
import uk.adzwoolly.mc.bombdefusal.BombManager;

public class DefuseCommand implements CommandExecutor{
	
	private BombManager bombManager;
	
	public DefuseCommand(BombManager bombManager){
		this.bombManager = bombManager;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		if(args.length == 2){
			if(bombManager.isArmed(args[0])){
				if(bombManager.getBombDefuseCode(args[0]) != null){
					if(args[1].equals(bombManager.getBombDefuseCode(args[0]))){
						bombManager.defuseBomb(args[0]);
					} else{
						sender.sendMessage(BombDefusalMain.msgPrefix + "Incorrect code!");
					}
				} else{
					sender.sendMessage(BombDefusalMain.msgPrefix + "That bomb does not exist.");
				}
			} else{
				sender.sendMessage(BombDefusalMain.msgPrefix + "That bomb does not exist.");
			}
			return true;
		}
		return false;
	}

}
