/**
 * 
 */
package me.MCMDEV.magic;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * GuiSpellsListener.java created by MCMDEV on 07.08.2017 at 13:02:24
 *
 * 
 */
public class GuiSpellsListener implements Listener {

	@EventHandler
	public void onSpellSelect(InventoryClickEvent e)	{
		if(e.getInventory().getName().equals("§bWähle den Zauber!"))	{
			if(Main.instance.itemStackLoreCheck(e.getCurrentItem(), "§2Teleportiere dich an den Ort den du erblickst"))	{
				Player p = (Player) e.getWhoClicked();
				
				if(p.hasPermission("magic.teleport"))	{
					ItemStack tpspell = p.getItemInHand();
					ItemMeta tpspellmeta = tpspell.getItemMeta();
					ArrayList<String> tpspelllore = new ArrayList<>();
					tpspelllore.add("§2Teleportation");
					tpspellmeta.setLore(tpspelllore);
					tpspell.setItemMeta(tpspellmeta);
					p.getInventory().setItemInHand(tpspell);
					e.setCancelled(true);
				}	else	{
					p.sendMessage(Main.prefix + "§cDu kennst diesen Zauber garnicht!");
				}
				
			}
			
			if(Main.instance.itemStackLoreCheck(e.getCurrentItem(), "§2Lässt dich magisch Verschwinden"))	{
				Player p = (Player) e.getWhoClicked();
				
				if(p.hasPermission("magic.invisibility"))	{
					ItemStack invisspell = p.getItemInHand();
					ItemMeta invisspellmeta = invisspell.getItemMeta();
					ArrayList<String> invisspelllore = new ArrayList<>();
					invisspelllore.add("§bUnsichtbarkeit");
					invisspellmeta.setLore(invisspelllore);
					invisspell.setItemMeta(invisspellmeta);
					p.getInventory().setItemInHand(invisspell);
					e.setCancelled(true);
				}	else	{
					p.sendMessage(Main.prefix + "§cDu kennst diesen Zauber garnicht!");
				}
				
			}
			
			if(Main.instance.itemStackLoreCheck(e.getCurrentItem(), "§5Lässt deine Gegner brennen"))	{
				Player p = (Player) e.getWhoClicked();
				
				if(p.hasPermission("magic.flameray"))	{
					ItemStack magicrayspell = p.getItemInHand();
					ItemMeta magicrayspellmeta = magicrayspell.getItemMeta();
					ArrayList<String> magicrayspelllore = new ArrayList<>();
					magicrayspelllore.add("§6Flammender Strahl");
					magicrayspellmeta.setLore(magicrayspelllore);
					magicrayspell.setItemMeta(magicrayspellmeta);
					p.getInventory().setItemInHand(magicrayspell);
					e.setCancelled(true);
				}	else	{
					p.sendMessage(Main.prefix + "§cDu kennst diesen Zauber garnicht!");
				}
				
			}
			
			if(Main.instance.itemStackLoreCheck(e.getCurrentItem(), "§dHeilt dich selber"))	{
				Player p = (Player) e.getWhoClicked();
				
				if(p.hasPermission("magic.healaura"))	{
					ItemStack healauraspell = p.getItemInHand();
					ItemMeta healauraspellmeta = healauraspell.getItemMeta();
					ArrayList<String> healauraspelllore = new ArrayList<>();
					healauraspelllore.add("§dHeilende Aura");
					healauraspellmeta.setLore(healauraspelllore);
					healauraspell.setItemMeta(healauraspellmeta);
					p.getInventory().setItemInHand(healauraspell);
					e.setCancelled(true);
				}	else	{
					p.sendMessage(Main.prefix + "§cDu kennst diesen Zauber garnicht!");
				}
				
			}
			
			if(Main.instance.itemStackLoreCheck(e.getCurrentItem(), "§eLässt dich durch die Zeit reisen"))	{
				Player p = (Player) e.getWhoClicked();
				
				if(p.hasPermission("magic.timemanipulation"))	{
					ItemStack timemanspell = p.getItemInHand();
					ItemMeta timemanspellmeta = timemanspell.getItemMeta();
					ArrayList<String> timemanspelllore = new ArrayList<>();
					timemanspelllore.add("§eZeitreise");
					timemanspellmeta.setLore(timemanspelllore);
					timemanspell.setItemMeta(timemanspellmeta);
					p.getInventory().setItemInHand(timemanspell);
					e.setCancelled(true);
				}	else	{
					p.sendMessage(Main.prefix + "§cDu kennst diesen Zauber garnicht!");
				}
				
			}
			
			if(Main.instance.itemStackLoreCheck(e.getCurrentItem(), "§7Lässt den Spieler das Item das er in den Hand hat droppen"))	{
				Player p = (Player) e.getWhoClicked();
				
				if(p.hasPermission("magic.forcedrop"))	{
					ItemStack forcedropspell = p.getItemInHand();
					ItemMeta forcedropspellmeta = forcedropspell.getItemMeta();
					ArrayList<String> forcedropspelllore = new ArrayList<>();
					forcedropspelllore.add("§7Gedankenkontrolle");
					forcedropspellmeta.setLore(forcedropspelllore);
					forcedropspell.setItemMeta(forcedropspellmeta);
					p.getInventory().setItemInHand(forcedropspell);
					e.setCancelled(true);
				}	else	{
					p.sendMessage(Main.prefix + "§cDu kennst diesen Zauber garnicht!");
				}
				
			}
			
		}
	}
	
	
}
