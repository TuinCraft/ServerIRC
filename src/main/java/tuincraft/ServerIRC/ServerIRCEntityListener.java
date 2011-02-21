package tuincraft.ServerIRC;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageByProjectileEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;

public class ServerIRCEntityListener extends EntityListener {
	private static final Logger log = Logger.getLogger("Minecraft");

	private ServerIRCPlugin plugin;

	public ServerIRCEntityListener(ServerIRCPlugin instance) {
		this.plugin = instance;
	}

	@Override
	public void onEntityDeath(EntityDeathEvent event) {
		Entity e = event.getEntity();

		if (e instanceof Player) {
			Player p = (Player) e;

			// TODO: find out how

			this.plugin.getServerBot().sendToAllChannels("Death: " + p.getDisplayName());
		}
	}

	@Override
	public void onEntityDamage(EntityDamageEvent event) 
	{
		if (!(event.getEntity() instanceof Player))
			return;
		
		if (event instanceof EntityDamageByProjectileEvent)
		{
			onEntityDamageByProjectile((EntityDamageByProjectileEvent) event);
		}
		else if (event instanceof EntityDamageByBlockEvent) 
		{
			onEntityDamageByBlock((EntityDamageByBlockEvent) event);
		}
		else if (event instanceof EntityDamageByEntityEvent) 
		{
			onEntityDamageByEntity((EntityDamageByEntityEvent) event);
		}
		//else
		//{
			log.log(Level.INFO, entityToString(event.getEntity()) +" got damaged ("+(event.getDamage())+"): " + event.getCause().toString());
			
			if (event.getEntity() instanceof LivingEntity && ((LivingEntity) event.getEntity()).getHealth() <= event.getDamage())
				this.plugin.getServerBot().sendToAllChannels(((Player)event.getEntity()).getName() + " died by " + event.getCause().toString().toLowerCase());
		//}
	}
	
	private String entityToString(Entity entity)
	{
		if (entity instanceof Player)
			return ((Player)entity).getDisplayName();
		
		return entity.toString();
	}

	private void onEntityDamageByProjectile(EntityDamageByProjectileEvent event) {
		this.plugin.getServerBot().sendToAllChannels(((Player)event.getEntity()).getName() + " got hit by a " + event.getProjectile().toString());
	}

	private void onEntityDamageByBlock(EntityDamageByBlockEvent event) {
		this.plugin.getServerBot().sendToAllChannels(((Player)event.getEntity()).getName() + " got hit by a " + event.getDamager().toString());
	}

	private void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		this.plugin.getServerBot().sendToAllChannels(((Player)event.getEntity()).getName() + " got hit by a " + event.getDamager().toString());
	}
}
