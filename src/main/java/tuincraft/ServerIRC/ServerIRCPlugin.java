package tuincraft.ServerIRC;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ServerIRCPlugin extends JavaPlugin {
	
	private String ircserver;
	private String autojoin;
	private boolean verbose;
	private String serverbotname;
	
	private ServerBot bot;
	
	private static final Logger log = Logger.getLogger("Minecraft");

	public void onDisable() {
		if(bot != null)
			bot.disconnect();
		log.log(Level.INFO, this.getDescription().getFullName() + " is disabled" );
	}
	
	public String getIrcServer()
	{
		return this.ircserver;
	}
	
	public String getAutoJoin()
	{
		return this.autojoin;
	}
	
	public boolean getVerbose()
	{
		return this.verbose;
	}

	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		PluginDescriptionFile pdfFile = this.getDescription();
		
		FileInputStream spf;
		Properties sp = new Properties();
		try {
			spf = new FileInputStream("server.properties");
			sp.load(spf);
			this.ircserver = sp.getProperty("irc.server","");
		} catch (FileNotFoundException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		} catch (IOException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
		
		if(this.ircserver.equals(""))
		{
			log.log(Level.SEVERE, pdfFile.getName() + ": set \"irc.server\" in server.properties" );		
			return;
		}
		
		pm.registerEvent(Type.CUSTOM_EVENT, new CustomListener(this), Priority.Normal, this);
		//pm.registerEvent(Type.PLAYER_COMMAND_PREPROCESS, new PlayerListener(this), Priority.Normal, this);
		//pm.registerEvent(Type.SERVER_COMMAND, new ServerListener(this), Priority.Normal, this);
		
		this.autojoin = sp.getProperty("irc.autojoin", "");
		this.verbose = Boolean.parseBoolean(sp.getProperty("irc.verbose", "false"));
		this.serverbotname = sp.getProperty("irc.serverbot", "MineBot");
		
		this.bot = new ServerBot(this);
		
		log.log(Level.INFO, pdfFile.getFullName() + " is enabled!");

	}

	public String getServerBotName() {
		return this.serverbotname;
	}

	public ServerBot getServerBot() {
		return this.bot;
	}

}
