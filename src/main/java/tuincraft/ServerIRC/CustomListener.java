package tuincraft.ServerIRC;

import java.util.logging.Logger;

import org.bukkit.event.CustomEventListener;
import org.bukkit.event.Event;

import com.amagineer.bukkit.minecraftchecker.MinecraftStatusEvent;
import com.herocraftonline.squallseed31.heroicdeath.HeroicDeathEvent;

public class CustomListener extends CustomEventListener {

	private static final Logger log = Logger.getLogger("Minecraft");

	private ServerIRCPlugin plugin;

	public CustomListener(ServerIRCPlugin instance) {
		this.plugin = instance;
	}

	@Override
	public void onCustomEvent(Event event) {
		try {
			if(event instanceof HeroicDeathEvent)
			{
				HeroicDeathEvent hde = (HeroicDeathEvent) event;
				this.plugin.getServerBot().sendToAllChannels(hde.getDeathCertificate().getMessage());
			}
		} catch(NoClassDefFoundError e)	{
			// I really want to ignore this.
		}
		try {
			if(event instanceof MinecraftStatusEvent)
			{
				MinecraftStatusEvent mse = (MinecraftStatusEvent) event;
				this.plugin.getServerBot().sendToAllChannels(mse.getReason());
			}
		} catch(NoClassDefFoundError e)	{
			// I really want to ignore this.
		}
	}

}
