package Plugins.hwsAPI.Commands;

import Plugins.hwsAPI.PluginsManageur.Main;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class CommandMaintenance extends Command {
	
	public CommandMaintenance() {
		super("maintenance", "Hws.Admin", "mMode");
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		
		if(args[0].contentEquals("on")){
			sender.sendMessage("Activé");
			Main.instance.mMode = true;
		}else if(args[0].contentEquals("off")){
			sender.sendMessage("Desactivé");
			Main.instance.mMode = false;
		}

	}

}
