package Plugins.hwsAPI.Commands;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandAnnonce extends Command {


	public CommandAnnonce() {
		super("annonce", "Hws.Admin");
	}
	@SuppressWarnings("deprecation")
	public void execute(CommandSender sender, String[] args) {

		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer player = (ProxiedPlayer)sender;
			
				
				if(args.length == 0) {
					player.sendMessage("§2La Commande est §7: §c/Annonce <Message> ");
				}
				
				if(args.length >= 1) {
					
					StringBuilder bc = new StringBuilder();
					for (String part : args){
						bc.append(part + " ");
					}
					
					BungeeCord.getInstance().broadcast("§7[§4Annonces§7] §4" + bc.toString());
					
				}
				
				
				return;
			}
			
	}

}
