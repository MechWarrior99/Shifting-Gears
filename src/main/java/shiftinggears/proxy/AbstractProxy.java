package shiftinggears.proxy;

import net.minecraft.item.Item;
import net.minecraft.world.World;

/**
 * @author shadowfacts
 */
public abstract class AbstractProxy {
	
	public abstract void registerItemModel(Item item, int meta, String name, String variant);

	public void registerItemModel(Item item, int meta, String name) { registerItemModel(item, meta, name, "inventory"); }

	public abstract String localize(String key, Object... params);

	public abstract World getClientWorld();

	public abstract void registerRenderers();

}
