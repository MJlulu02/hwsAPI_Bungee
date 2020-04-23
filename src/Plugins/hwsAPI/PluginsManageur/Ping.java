package Plugins.hwsAPI.PluginsManageur;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class Ping implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onProxyPing(ProxyPingEvent e) {
		ServerPing sp = e.getResponse();
		if (sp == null)
			return;
		
		String MOTD;
		
		if(Main.instance.mMode == true ) {
			sp.setVersion(new ServerPing.Protocol("Maintenance", 4));
			MOTD = ChatColor.translateAlternateColorCodes('&', "&7(&f&kH&7) &4Le Serveur est actuellement en Maintenance ! &7(&f&kH&7)");
		}else {
			sp.setPlayers(new ServerPing.Players(1, BungeeCord.getInstance().getPlayers().size(), e.getResponse().getPlayers().getSample()));
			MOTD = "Bienvenue sur le serveur Connard !!";
		}

		sp.setDescription(MOTD);
		e.setResponse(sp);
	}
}