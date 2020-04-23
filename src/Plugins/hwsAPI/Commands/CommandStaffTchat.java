package Plugins.hwsAPI.Commands;

import Plugins.hwsAPI.PluginsManageur.Main;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandStaffTchat extends Command {

	public CommandStaffTchat() {
		super("TchatStaff", "Hws.TchatStaff");
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length < 1) {
			sender.sendMessage("§2La Commande est §7: §c/Tchatstaff <Message>");

			return;
		}
		
		String msg = "";
		for(int i = 0; i < args.length; i++) {
			msg = msg+" "+args[i];
		}

		if (sender instanceof ProxiedPlayer) {
			
			SendMessageAtAll("§7[§aTchatStaff§7]§f"+  sender.getName() + " §7: §f"+ msg);
		}
	}
	
	private void SendMessageAtAll(String msg) {
		TextComponent tc = new TextComponent();
		tc.setText(msg);
		tc.setClickEvent(new ClickEvent(Action.SUGGEST_COMMAND, "/TchatStaff "));
		tc.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder(ChatColor.DARK_AQUA + "Envoyer un message au Staff").create()));
		
		for(ProxiedPlayer pls : Main.instance.StaffListe) {
			pls.sendMessage(tc);
		}
	}

}
