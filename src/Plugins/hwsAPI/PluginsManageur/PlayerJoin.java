package Plugins.hwsAPI.PluginsManageur;

import Plugins.hwsAPI.DataManageur.DataManageur;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerJoin implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onjoin(PostLoginEvent e) {
		ProxiedPlayer p = e.getPlayer();
		
		if (new HWSAPI().hwsConfig.getMongoConnection() != null && new HWSAPI().hwsConfig.GetJedis() != null) {
			new DataManageur(p).getPlayerData();
		} else {
			if (p.getPermissions().contains("HwsApi.Admin")) {
				if (new HWSAPI().hwsConfig.GetJedis() == null && new HWSAPI().hwsConfig.getMongoDB() == null)
					p.disconnect("§4Erreur Aucune Base de donné Connecté !");
				if (new HWSAPI().hwsConfig.getMongoDB() == null)
					p.disconnect("§4Erreur MongoDB n'est pas Connecté !");
				if (new HWSAPI().hwsConfig.GetJedis() == null)
					p.disconnect("§4Erreur Redis n'est pas Connecté !");
			} else {
				p.disconnect("§4Impossible de se connecté, merci de contacté un Administrateur !");
			}
		}
		
		if(p.hasPermission("Hws.TchatStaff"))
			Main.instance.StaffListe.add(p);
	}

	@EventHandler
	public void onQuit(PlayerDisconnectEvent e) {
		ProxiedPlayer p = e.getPlayer();
		if(Main.instance.StaffListe.contains(p))
				Main.instance.StaffListe.remove(p);

		//Add insyncron task (5-6 secondes)
		new DataManageur(p).setPlayerData();
	}
}
