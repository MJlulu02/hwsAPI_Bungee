package Plugins.hwsAPI.PluginsManageur;

import Plugins.hwsAPI.Utils.HWSConfig;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin implements Listener {

	/*
	 * Ceci est L'API HWS ! Elle � pour but de facilit� le d�veloppement des plugin
	 * du serveur HWS. Et de g�rer toutes communications de donn�es au seing des
	 * serveurs Minecraft.
	 * 
	 * @author MJlulu02
	 * 
	 * @version 0.0.1
	 */

	public static Main instance = null;
	public HWSConfig hwsConfig;

	public void onEnable() {
		System.out.println("[HwsAPI_Bungee] Enabled");

		this.getProxy().getPluginManager().registerListener(this, this);
		ProxyServer.getInstance().getPluginManager().registerListener(this, new PlayerJoin());

		this.hwsConfig = new HWSConfig();
	}

	public void onDisable() {
		System.out.println("[HwsAPI_Bungee] Disable");
	}
}