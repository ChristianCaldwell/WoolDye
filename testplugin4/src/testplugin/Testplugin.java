package testplugin;

import java.util.Random;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;
import org.bukkit.material.Wool;
import org.bukkit.plugin.java.JavaPlugin;

public class Testplugin extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {

	}

	private static Random rand = new Random();

	public static int randInt(int min, int max) {

		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	@EventHandler
	public void playerInteract(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Block b = event.getClickedBlock();
			if (b.getType() == Material.WOOL) {
				ItemStack d = event.getPlayer().getInventory().getItemInMainHand();
				if (d.getData() instanceof Dye) {
					DyeColor color = ((Dye) event.getPlayer().getInventory().getItemInMainHand().getData()).getColor();
					BlockState state = b.getState();
					Wool WoolInfo = (Wool) state.getData();
					WoolInfo.setColor(color);
					state.setData(WoolInfo);
					state.update();
					if (randInt(1, 5) == 1) {
						if (d.getAmount() > 1)
							d.setAmount(d.getAmount() - 1);
						else {
							event.getPlayer().getInventory().remove(d);
						}
					}

				}

			}

		}
		return;
	}

}
