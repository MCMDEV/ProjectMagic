/**
 * 
 */
package me.MCMDEV.magic;

import org.apache.commons.lang.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * CMD_setMana.java created by MCMDEV on 07.08.2017 at 11:26:26
 *
 * 
 */
public class CMD_setMana implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender s, Command c, String lbl, String[] args) {
		if(s instanceof Player){
			if(s.hasPermission("magic.setmana"))	{
				if(args.length == 2){
					Player p2 = Bukkit.getPlayer(args[0]);
					if(p2 != null){
						if(NumberUtils.isNumber(args[1]))	{
							int manatoset = Integer.parseInt(args[1]);
								Main.instance.setMana(p2, manatoset);
								s.sendMessage(Main.prefix+"Du hast das Mana von§e " + args[0] + "§b auf §e" + manatoset + " §bgesetzt!");
						}	else	{
							s.sendMessage(Main.prefix+"§cDas ist keine Zahl!");
						}
					}	else	{
						s.sendMessage(Main.prefix+"§cDieser Spieler ist nicht online!");
					}
					
				}	else	{
					s.sendMessage(Main.prefix + "§cUps, du hast was falsches Eingegeben! Nutze /setmana <Spielername> <Mana>");
				}
			}	
		}	else	{
				if(args.length == 2){
					Player p2 = Bukkit.getPlayer(args[0]);
					if(p2 != null){
						if(NumberUtils.isNumber(args[1]))	{
							int manatoset = Integer.parseInt(args[1]);
								Main.instance.setMana(p2, manatoset);
								Bukkit.getConsoleSender().sendMessage(Main.prefix+"Du hast das Mana von§e " + args[0] + "§b auf §e" + manatoset + " §bgesetzt!");
						}	else	{
							Bukkit.getConsoleSender().sendMessage(Main.prefix+"§cDas ist keine Zahl!");
						}
					}	else	{
						Bukkit.getConsoleSender().sendMessage(Main.prefix+"§cDieser Spieler ist nicht online!");
					}
					
				}	else	{
					s.sendMessage(Main.prefix + "§cUps, du hast was falsches Eingegeben! Nutze /setmana <Spielername> <Mana>");
				}
		}
		return false;
	}

}
