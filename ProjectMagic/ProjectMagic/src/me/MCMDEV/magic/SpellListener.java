/**
 * 
 */
package me.MCMDEV.magic;

import java.util.HashMap;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 * SpellListener.java created by MCMDEV on 07.08.2017 at 10:28:40
 *
 * 
 */
public class SpellListener implements Listener {
	
	int t;
	public HashMap<Player, Boolean> inInv = new HashMap<>();
	public HashMap<Player, Location> timemaniloc = new HashMap<>();
	boolean wait = false;

	@EventHandler
	public void onSpellCast(PlayerInteractEvent e)	{
		
		if(e.getPlayer().getItemInHand().getType() == Material.STICK)	{
			if(Main.instance.itemStackNameCheck(e.getPlayer().getItemInHand(), "§9Zauberstab"))	{
			if(e.getPlayer().isSneaking())	{
				if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
					if(e.getPlayer().hasPermission("magic.ismagic"))	{
						Main.instance.regSelectorItems();
						e.getPlayer().openInventory(Main.instance.inv);
					}
				}
			}
		}
	}
		
			if(Main.instance.itemLoreCheck(e.getPlayer(), "§2Teleportation")){
				if(e.getAction() == Action.RIGHT_CLICK_AIR)	{
					if(e.getPlayer().hasPermission("magic.teleport"))	{
						Location toTP = e.getPlayer().getTargetBlock((Set<Material>)null, 100).getLocation();
						e.getPlayer().getWorld().spawnParticle(Particle.PORTAL, e.getPlayer().getLocation(), 10);
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
					
							@Override
							public void run() {
							if(Main.instance.getMana(e.getPlayer()) <= (int)e.getPlayer().getLocation().distance(toTP))	{
								Main.instance.sendActionBarMessage(e.getPlayer(), Main.prefix + "§cDu hast nicht genug Mana!");
							}	else	{
								float yaw = e.getPlayer().getLocation().getYaw();
								float pitch = e.getPlayer().getLocation().getPitch();
								Main.instance.subMana(e.getPlayer(), (int)e.getPlayer().getLocation().distance(toTP));
								Main.instance.sendActionBarMessage(e.getPlayer(), Main.prefix + "Du wurdest teleportiert!");
								e.getPlayer().teleport(toTP);
								e.getPlayer().teleport(new Location(e.getPlayer().getWorld(), e.getPlayer().getLocation().getX(), e.getPlayer().getLocation().getY(), e.getPlayer().getLocation().getZ(), yaw, pitch));
								if(e.getPlayer().getLocation().getBlock().getType() != Material.AIR)	{
									e.getPlayer().teleport(e.getPlayer().getLocation().add(0, 1, 0));
								}
								e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ENDERMEN_TELEPORT, 1, 1);
						}
						
						
					}
				}, 20*3);
				}
			}
			}
			
			
			if(Main.instance.itemLoreCheck(e.getPlayer(), "§bUnsichtbarkeit")){
				if(e.getAction() == Action.RIGHT_CLICK_AIR)	{
					if(e.getPlayer().hasPermission("magic.invisibility"))	{
						e.getPlayer().getWorld().spawnParticle(Particle.CLOUD, e.getPlayer().getLocation(), 100);
						
						if(!inInv.containsKey(e.getPlayer()))	{
							inInv.put(e.getPlayer(), false);
						}
						
						if(inInv.get(e.getPlayer()) == false){
						if(Main.instance.getMana(e.getPlayer()) >= 50)	{
							Main.instance.subMana(e.getPlayer(), 50);
							for(Player all : Bukkit.getOnlinePlayers())	{
								if(all.hasPermission("magic.seeInvis"))	{
									inInv.put(e.getPlayer(), true);
								}	else	{
									all.hidePlayer(e.getPlayer());
									e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 0.4F);
									
									t = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
										
										int time = 10;
										
										@Override
										public void run() {
											
											
											time--;
											
											Main.instance.sendActionBarMessage(e.getPlayer(), Main.prefix + "Du bist noch §e" + time + " §bSekunden unsichtbar!");
											
											if(time == 0)	{
												Main.instance.sendActionBarMessage(e.getPlayer(), Main.prefix + "Du bist nun wieder sichtbar!");
												time = 10;
												Bukkit.getScheduler().cancelTask(t);
												
											}
											
										}
										
									}, 20, 20);
									
									Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
										
										@Override
										public void run() {
											for(Player all : Bukkit.getOnlinePlayers()){
												if(all.hasPermission("magic.seeInvis"))	{
													inInv.put(e.getPlayer(), false);
												}	else	{
													all.showPlayer(e.getPlayer());
													e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 1F);
													e.getPlayer().getWorld().spawnParticle(Particle.CLOUD, e.getPlayer().getLocation(), 100);
												}
											}
											
										}
									}, 20*10);
									
								}
							}
						}	else	{
							Main.instance.sendActionBarMessage(e.getPlayer(), Main.prefix + "§cDu hast nicht genug Mana!");
						}
						}	else	{
							e.getPlayer().sendMessage(Main.prefix + "§cDu bist schon unsichtbar!");
						}
						
					}
						
			}
			}
			
			if(Main.instance.itemLoreCheck(e.getPlayer(), "§6Flammender Strahl")){
				if(e.getAction() == Action.RIGHT_CLICK_AIR)	{
					if(e.getPlayer().hasPermission("magic.flameray"))	{
						
						if(Main.instance.getMana(e.getPlayer()) >= 50)	{
							Main.instance.subMana(e.getPlayer(), 50);
							
							e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.ITEM_FIRECHARGE_USE, 1, 1);
							
							for(Block block : e.getPlayer().getLineOfSight((Set<Material>)null, 10))	{
								block.getWorld().spawnParticle(Particle.FLAME, block.getLocation(), 0);
								for(Entity ent : block.getLocation().getWorld().getNearbyEntities(block.getLocation(), 1, 1, 1))	{
									ent.setFireTicks(20*5);
									e.getPlayer().setFireTicks(0);
								}
							}
					        
							
							
						}	else	{
							Main.instance.sendActionBarMessage(e.getPlayer(), Main.prefix + "§cDu hast nicht genug Mana!");
						}
						
						
					}
						
			}
			}
			
			if(Main.instance.itemLoreCheck(e.getPlayer(), "§dHeilende Aura")){
				if(e.getAction() == Action.RIGHT_CLICK_AIR)	{
					if(e.getPlayer().hasPermission("magic.healaura"))	{
						
					if(Main.instance.getMana(e.getPlayer()) >= 20)	{
						
						e.getPlayer().getWorld().playSound(e.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
						e.getPlayer().getWorld().playEffect(e.getPlayer().getLocation(), Effect.HEART, 1);
						Main.instance.sendActionBarMessage(e.getPlayer(), Main.prefix + "§dDu hast dich geheilt!");
						
						if(e.getPlayer().getHealth() >= 20 - 2.5){
							e.getPlayer().setHealth(20);
						}	else	{
							e.getPlayer().setHealth(e.getPlayer().getHealth() + 2.5);
						}
						
					}	else	{
						Main.instance.sendActionBarMessage(e.getPlayer(), Main.prefix + "§cDu hast nicht genug Mana!");
					}
						
					}
						
			}
			}
			
			if(Main.instance.itemLoreCheck(e.getPlayer(), "§eZeitreise")){
				if(e.getAction() == Action.RIGHT_CLICK_AIR)	{
					if(e.getPlayer().hasPermission("magic.timemanipulation"))	{
						
					if(Main.instance.getMana(e.getPlayer()) >= 25)	{
						Main.instance.subMana(e.getPlayer(), 25);
						
						if(timemaniloc.containsKey(e.getPlayer()))	{
							e.getPlayer().teleport(timemaniloc.get(e.getPlayer()));
							e.getPlayer().getWorld().playEffect(e.getPlayer().getLocation(), Effect.PORTAL, 1);
							Main.instance.sendActionBarMessage(e.getPlayer(), Main.prefix + "Du bist durch die Zeit gereist!");
						}
						
					}	else	{
						Main.instance.sendActionBarMessage(e.getPlayer(), Main.prefix + "§cDu hast nicht genug Mana!");
					}
						
					}
						
			}	else if(e.getAction() == Action.LEFT_CLICK_BLOCK){
				timemaniloc.put(e.getPlayer(), e.getPlayer().getLocation());
				Main.instance.sendActionBarMessage(e.getPlayer(), Main.prefix + "Du hast deinen Zeitreisepunkt gesetzt!");
			}
			}
			
			
	}
	
	
	@EventHandler
	public void onSpellCastClickEntity(PlayerInteractEntityEvent e)	{
		if(Main.instance.itemLoreCheck(e.getPlayer(), "§7Gedankenkontrolle")){
				if(e.getPlayer().hasPermission("magic.forcedrop"))	{
					
				if(!wait)	{
					
				if(Main.instance.getMana(e.getPlayer()) >= 75)	{
					Main.instance.subMana(e.getPlayer(), 75);
					
					if(e.getRightClicked().getType() == EntityType.PLAYER){
						Player p2 = (Player) e.getRightClicked();
						ItemStack itd = p2.getInventory().getItemInHand();
						itd.setAmount(1);
						if(p2.getItemInHand().getAmount() == 1){
							p2.setItemInHand(null);
						}	else	{
							p2.getItemInHand().setAmount(p2.getItemInHand().getAmount() - 1);
						}
						Item dropped = p2.getWorld().dropItem(p2.getEyeLocation(), itd);
						dropped.setVelocity(p2.getEyeLocation().getDirection().normalize().multiply(0.3));
						
						wait = true;
								
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.instance, new Runnable() {
							
							@Override
							public void run() {
								wait = false;
								
							}
						}, 20*1);
						
					}	else	{
						Main.instance.sendActionBarMessage(e.getPlayer(), Main.prefix + "§cDieser Zauber funktioniert nur bei Spielern");
					}
					
				}	else	{
					Main.instance.sendActionBarMessage(e.getPlayer(), Main.prefix + "§cDu hast nicht genug Mana!");
				}
				
				}
					
				}
					
		
		}
	}
}
	
	

