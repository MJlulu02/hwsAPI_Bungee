package Plugins.hwsAPI.PluginsManageur;

import java.util.ArrayList;

import Plugins.hwsAPI.Commands.CommandAnnonce;
import Plugins.hwsAPI.Commands.CommandHub;
import Plugins.hwsAPI.Commands.CommandMaintenance;
import Plugins.hwsAPI.Commands.CommandMsg;
import Plugins.hwsAPI.Commands.CommandStaffTchat;
import Plugins.hwsAPI.Utils.HWSConfig;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public class Main extends Plugin implements Listener {

	/*
	 * Ceci est L'API HWS ! Elle à pour but de facilité le développement des plugin
	 * du serveur HWS. Et de gérer toutes communications de données au seing des
	 * serveurs Minecraft.
	 * 
	 * @author MJlulu02
	 * 
	 * @version 0.0.1
	 */
	
	public static Main instance;
	public HWSConfig hwsConfig;
	public boolean mMode = false;
	public ArrayList<ProxiedPlayer> StaffListe = new ArrayList<ProxiedPlayer>();

	@SuppressWarnings("static-access")
	public void onEnable() {
		System.out.println("[HwsAPI_Bungee] Enabled");

		this.getProxy().getPluginManager().registerListener(this, this);
		ProxyServer.getInstance().getPluginManager().registerListener(this, new PlayerJoin());
		ProxyServer.getInstance().getPluginManager().registerListener(this, new Ping());

		ProxyServer.getInstance().getPluginManager().registerCommand(this, new CommandAnnonce());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new CommandMsg());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new CommandStaffTchat());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new CommandHub());
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new CommandMaintenance());

		this.instance = this;
		this.hwsConfig = new HWSConfig();

		// Test remove aprés !!
		this.hwsConfig.setRedis_ip("51.77.149.191");
		this.hwsConfig.setRedis_pass("b8tXJ/QyjSQInxPPTlPND5yOmaODjhJbnrD75F939Fe/xjPIcBzoA71yDPUgkxSyz/sSn8Wgln4ImDJA");
		this.hwsConfig.connect_redis();

		this.hwsConfig.setMongo_ip("51.77.149.191");
		this.hwsConfig.setMongo_pass("WN6uN44g2eA7yqse2mLC9RB8e9pUM43h");
		this.hwsConfig.setMongo_usr("admin");
		this.hwsConfig.connect_mongo();
	}

	public void onDisable() {
		System.out.println("[HwsAPI_Bungee] Disable");
	}
}