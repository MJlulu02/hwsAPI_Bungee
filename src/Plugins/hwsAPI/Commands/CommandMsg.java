package Plugins.hwsAPI.Commands;

import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ClickEvent.Action;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CommandMsg extends Command {

	public CommandMsg() {
		super("msg");
	}

	@SuppressWarnings("deprecation")
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (args.length < 2) {
			sender.sendMessage("§2La Commande est §7: §c/msg <Pseudo> <Message>");

			return;
		}
		
		String msg = "";
		for(int i = 1; i < args.length; i++) {
			msg = msg+" "+args[i];
		}

		if (sender instanceof ProxiedPlayer) {
			ProxiedPlayer p = (ProxiedPlayer) sender;

			if (args[0].equalsIgnoreCase(p.getName())) {
				p.sendMessage("§2Vous ne pouvez pas vous envoyez un message à vous même !");

				return;
			}

			ProxiedPlayer pget = (ProxiedPlayer) BungeeCord.getInstance().getPlayer(args[0]);

			if (pget != null) {
				this.BuildMessage(p, pget.getName(), "§4Vous §7→ §a" + pget.getName() + " §7: §f" + msg);
				this.BuildMessage(pget, p.getName(), "§a" + p.getName() + " §7→ §4Vous §7: §f" + msg);
			} else {
				p.sendMessage("§2Le Joueur §a" + args[0] + " §2N'est pas connecté !");
			}
		} else {
			ProxiedPlayer pget = (ProxiedPlayer) BungeeCord.getInstance().getPlayer(args[0]);
			if (pget != null) {
				sender.sendMessage("§4Vous §7→ §a" + pget.getName() + " §7: §f" + msg);
				pget.sendMessage("§a" + sender.getName() + " §7→ §4Vous §7: §f" + msg);
			} else {
				sender.sendMessage("§2Le Joueur §a" + args[0] + " §2N'est pas connecté !");
			}
		}
	}

	private void BuildMessage(ProxiedPlayer p, String p1, String s) {
		TextComponent tc = new TextComponent();
		tc.setText(s);
		tc.setClickEvent(new ClickEvent(Action.SUGGEST_COMMAND, "/msg " + p1 + " "));
		tc.setHoverEvent(new HoverEvent(net.md_5.bungee.api.chat.HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder(ChatColor.DARK_AQUA + "Répondre à " + p1).create()));
		p.sendMessage(tc);
	}

}
