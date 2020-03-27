package Plugins.hwsAPI.PluginsManageur;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class EventManager {

	public static void registerEvents(Plugin pl) {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PLayerJoin(), pl);
	}
}
