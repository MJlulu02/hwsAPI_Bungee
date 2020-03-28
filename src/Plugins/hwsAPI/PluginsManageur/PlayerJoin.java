package Plugins.hwsAPI.PluginsManageur;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerJoin implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onjoin(PostLoginEvent e) {
		ProxiedPlayer p = e.getPlayer();
		if (new HWSAPI().hwsConfig.getMongoDB() != null && new HWSAPI().hwsConfig.GetJedis() != null) {
			// Get Player Data
		} else {
			if (p.getPermissions().contains("HwsApi.Admin")) {
				if (new HWSAPI().hwsConfig.GetJedis() == null && new HWSAPI().hwsConfig.getMongoDB() == null)
					p.disconnect("&4Erreur Aucune Base de donn� Connect� !");
				if (new HWSAPI().hwsConfig.getMongoDB() == null)
					p.disconnect("&4Erreur MongoDB n'est pas Connect� !");
				if (new HWSAPI().hwsConfig.GetJedis() == null)
					p.disconnect("&4Erreur Redis n'est pas Connect� !");
			} else {
				p.disconnect("&4Impossible de se connect�, merci de contact� un Administrateur !");
			}
		}
	}

}
