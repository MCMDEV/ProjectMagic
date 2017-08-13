/**
 * 
 */
package me.MCMDEV.magic;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * CMD_getMana.java created by MCMDEV on 07.08.2017 at 11:38:52
 *
 * 
 */
public class CMD_getMana implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command c, String lbl, String[] args) {
		if(s instanceof Player){
			Player p = (Player) s;
			if(args.length == 0)	{
				if(p.hasPermission("magic.ismagic"))	{
					p.sendMessage(Main.prefix + "Du hast §e" + Main.instance.getMana(p) + " §bMana!");
				}	else	{
					p.sendMessage(Main.prefix + "§cDu bist nicht magisch!");
				}
			}	else if(args.length == 1)	{
				if(p.hasPermission("magic.getothersmana"))	{
					Player p2 = Bukkit.getPlayer(args[0]);
					if(p2 != null){
						if(p2.hasPermission("magic.ismagic"))	{
							p.sendMessage(Main.prefix + "Das Mana von §e" + p2.getName() + "§b beträgt §e" + Main.instance.getMana(p2));
						}	else	{
							p.sendMessage(Main.prefix + "§e" + p2.getName() + "§bist nicht magisch!");
						}
					}	else	{
						p.sendMessage(Main.prefix + "§cDieser Spieler ist nicht online!");
					}
				}
			}	else	{
				s.sendMessage(Main.prefix + "§cUps, du hast was falsches Eingegeben! Nutze /getmana <Spielername>");
			}
		}	else	{
			if(args.length == 1){
				Player p2 = Bukkit.getPlayer(args[0]);
				if(p2 != null){
					if(p2.hasPermission("magic.ismagic"))	{
						Bukkit.getConsoleSender().sendMessage(Main.prefix + "Das Mana von §e" + p2.getName() + "§b beträgt §e" + Main.instance.getMana(p2));
					}	else	{
						Bukkit.getConsoleSender().sendMessage(Main.prefix + "§e" + p2.getName() + "§bist nicht magisch!");
					}
				}	else	{
					Bukkit.getConsoleSender().sendMessage(Main.prefix + "§cDieser Spieler ist nicht online!");
				}
			}	else	{
				Bukkit.getConsoleSender().sendMessage(Main.prefix + "§cSo kannst du diesen Befehl nur als Spieler ausführen! Nutze /getmana <Spielername>");
			}
		}
		return false;
	}

}
