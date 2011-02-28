package tuincraft.ServerIRC;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jibble.pircbot.IrcException;
import org.jibble.pircbot.NickAlreadyInUseException;
import org.jibble.pircbot.PircBot;

public class ServerBot extends PircBot {
	private static final Logger log = Logger.getLogger("Minecraft");

	public ServerBot(ServerIRCPlugin plugin) {
		try {
			setName(plugin.getServerBotName());
			super.connect(plugin.getIrcServer());
			
			this.joinChannel(plugin.getAutoJoin());
		} catch (NickAlreadyInUseException e) {
			log.log(Level.SEVERE, "Nick already in use: " + e.getMessage());
		} catch (IOException e) {
			log.log(Level.SEVERE, "Failed to connect: " + e.getMessage());
		} catch (IrcException e) {
			log.log(Level.SEVERE, "Failed to connect: " + e.getMessage());
		}
	}

	public void sendToAllChannels(String message) {
		for (String channel  : this.getChannels())
		{
			this.sendMessage(channel, ColorMap.toIrc(message));
		}
	}
}
