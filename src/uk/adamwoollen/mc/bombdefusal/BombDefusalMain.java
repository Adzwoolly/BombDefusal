package uk.adamwoollen.mc.bombdefusal;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import uk.adamwoollen.mc.bombdefusal.commnds.DefuseCommand;

public class BombDefusalMain extends JavaPlugin{
	
	public static final String msgPrefix = ChatColor.DARK_PURPLE + "[BombDefusal] " + ChatColor.RESET;
	public static final String msgNoPermission = "You do not have the required permissions to run this command.";
	
	private final BombManager bombManager = new BombManager(this);
	
	//File bountyRecords = new File("plugins/Bounty/bountyRecords.txt");
	
	//Fired when plugin is first enabled
	@Override
	public void onEnable(){
		
		//register listener and commands
		getServer().getPluginManager().registerEvents(new MyListener(bombManager), this);
		registerCommands();
		
		//If there is no bounty save file, create a new empty one
		/*try {
			bountyRecords.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	
	//Fired when plugin is disabled
	@Override
	public void onDisable(){
		//
	}
	
	/**
	 * Method - Tells Bukkit what classes should be used to execute commands
	 * @author Adzwoolly (Adam Woollen)
	 */
	private void registerCommands(){
		this.getCommand("defuse").setExecutor(new DefuseCommand(bombManager));
	}
	
}
