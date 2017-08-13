/**
 * 
 */
package me.MCMDEV.magic;

import java.awt.List;
import java.sql.Savepoint;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import net.minecraft.server.v1_12_R1.ChatMessageType;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;

/**
 * Main.java created by MCMDEV on 07.08.2017 at 10:26:47
 *
 * 
 */
public class Main extends JavaPlugin {

	public static Main instance;
	public static int Mana;
	Inventory inv = Bukkit.createInventory(null, 27, "§bWähle den Zauber!");
	
	public static String prefix;
	
	@Override
	public void onEnable() {
		instance = this;
		Bukkit.getServer().getPluginManager().registerEvents(new SpellListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new GuiSpellsListener(), this);
		getConfig().addDefault("prefix", "§bMagic §7| §b");
		getConfig().options().copyDefaults(true);
		prefix = getConfig().getString("prefix");
		getCommand("setmana").setExecutor(new CMD_setMana());
		getCommand("getmana").setExecutor(new CMD_getMana());
		
	}

	public int getMana(Player p) {
		return Main.instance.getConfig().getInt(p.getName());
	}

	public void setMana(Player p, int mana) {
		Main.instance.getConfig().set(p.getName(), mana);
		Main.instance.saveConfig();
	}
	
	public void addMana(Player p, int mana) {
		setMana(p, getMana(p) + mana);
	}
	
	public void subMana(Player p, int mana) {
		setMana(p, getMana(p) - mana);
	}
	
	public void sendActionBarMessage(Player p, String msg){
		
		IChatBaseComponent ichatmsg = new IChatBaseComponent.ChatSerializer().a("{\"text\": \"" + msg + "\"}");
		((CraftPlayer)p).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(ichatmsg, ChatMessageType.GAME_INFO));
		
	}
	
	public void regSelectorItems(){
		ItemStack tpspell = new ItemStack(Material.ENDER_PEARL, 1, (byte)0);
		ItemMeta tpspellmeta = tpspell.getItemMeta();
		tpspellmeta.setDisplayName("§2Teleportation");
		ArrayList<String> tplore = new ArrayList<>();
		tplore.add("§2Teleportiere dich an den Ort den du erblickst");
		tpspellmeta.setLore(tplore);
		tpspell.setItemMeta(tpspellmeta);
		inv.setItem(0, tpspell);
		
		ItemStack invisspell = new ItemStack(Material.INK_SACK, 1, (byte)15);
		ItemMeta invisspellmeta = tpspell.getItemMeta();
		invisspellmeta.setDisplayName("§bUnsichtbarkeit");
		ArrayList<String> invislore = new ArrayList<>();
		invislore.add("§2Lässt dich magisch Verschwinden");
		invisspellmeta.setLore(invislore);
		invisspell.setItemMeta(invisspellmeta);
		inv.setItem(1, invisspell);
		
		ItemStack magicrayspell = new ItemStack(Material.BLAZE_POWDER, 1, (byte)0);
		ItemMeta magicrayspellmeta = magicrayspell.getItemMeta();
		magicrayspellmeta.setDisplayName("§6Flammender Strahl");
		ArrayList<String> magicraylore = new ArrayList<>();
		magicraylore.add("§5Lässt deine Gegner brennen");
		magicrayspellmeta.setLore(magicraylore);
		magicrayspell.setItemMeta(magicrayspellmeta);
		inv.setItem(2, magicrayspell);
		
		ItemStack healauraspell = new ItemStack(Material.GOLDEN_APPLE, 1, (byte)0);
		ItemMeta healauraspellmeta = healauraspell.getItemMeta();
		healauraspellmeta.setDisplayName("§dHeilende Aura");
		ArrayList<String> healauralore = new ArrayList<>();
		healauralore.add("§dHeilt dich selber");
		healauraspellmeta.setLore(healauralore);
		healauraspell.setItemMeta(healauraspellmeta);
		inv.setItem(3, healauraspell);
		
		ItemStack timemanspell = new ItemStack(Material.WATCH, 1, (byte)0);
		ItemMeta timemanspellmeta = timemanspell.getItemMeta();
		timemanspellmeta.setDisplayName("§eZeitreise");
		ArrayList<String> timemanlore = new ArrayList<>();
		timemanlore.add("§eLässt dich durch die Zeit reisen");
		timemanspellmeta.setLore(timemanlore);
		timemanspell.setItemMeta(timemanspellmeta);
		inv.setItem(4, timemanspell);
		
		ItemStack forcedropspell = new ItemStack(Material.SKULL_ITEM, 1, (byte)3);
		ItemMeta forcedropspellmeta = forcedropspell.getItemMeta();
		forcedropspellmeta.setDisplayName("§7Gedankenkontrolle");
		ArrayList<String> forcedroplore = new ArrayList<>();
		forcedroplore.add("§7Lässt den Spieler das Item das er in den Hand hat droppen");
		forcedropspellmeta.setLore(forcedroplore);
		forcedropspell.setItemMeta(forcedropspellmeta);
		inv.setItem(5, forcedropspell);
	}
	
	public boolean itemLoreCheck(Player player, String in)
	{
	     ItemStack i = player.getItemInHand();
	     if(i == null)return false;
	     if(!i.hasItemMeta())return false;
	     ItemMeta im = i.getItemMeta();
	     if(!im.hasLore())return false;
	     String dn = im.getLore().get(0);
	     return dn.indexOf(in) > -1;
	}
	
	public boolean itemStackLoreCheck(ItemStack i, String in)
	{
	     if(i == null)return false;
	     if(!i.hasItemMeta())return false;
	     ItemMeta im = i.getItemMeta();
	     if(!im.hasLore())return false;
	     String dn = im.getLore().get(0);
	     return dn.indexOf(in) > -1;
	}
	
	public boolean itemStackNameCheck(ItemStack i, String in)
	{
	     if(i == null)return false;
	     if(!i.hasItemMeta())return false;
	     ItemMeta im = i.getItemMeta();
	     if(!im.hasDisplayName())return false;
	     String dn = im.getDisplayName();
	     return dn.indexOf(in) > -1;
	}
	
	
	

}
