package Plugins.hwsAPI.Commands;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandHub extends Command {

	public CommandHub() {
		super("hub");
	}
	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		
		if(sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;
			ServerInfo si = p.getServer().getInfo();
			
			if(!si.getName().contains("Hub")) {
				si = BungeeCord.getInstance().getServerInfo("Hub");
				if(si != null) {
					p.connect(si);
				}else {
					p.sendMessage("§4Une Erreur est Survenue §7: §4Aucun Hub trouvé !");
				}
			}else {
				p.sendMessage("§4Une Erreur est Survenue §7: §4Tu es déjà sur le Hub !");
			}
		}
		
		return;
	}
}
