package Plugins.hwsAPI.PluginsManageur;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	public static Main instance;
	
	public void onEnable(){
		System.out.println("[HwsAPI_Bungee] Enabled");
		EventManager.registerEvents(this);
		this.instance = this;
	}
	
	public void onDisable(){
		System.out.println("[HwsAPI_Bungee] Disable");
	}
	
}
