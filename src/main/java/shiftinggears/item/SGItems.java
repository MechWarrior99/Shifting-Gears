package shiftinggears.item;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import shiftinggears.item.material.ItemIngot;
import shiftinggears.item.material.ItemNugget;

import java.lang.reflect.Field;

/**
 * @author shadowfacts
 */
public class SGItems {

	public static ItemIngot ingot = new ItemIngot();
	public static ItemNugget nugget = new ItemNugget();

	public static void init() {
		for (Field f : SGItems.class.getFields()) {
			if (Item.class.isAssignableFrom(f.getType())) {
				try {
					register((Item)f.get(null));
				} catch (ReflectiveOperationException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	private static void register(Item item) {
		GameRegistry.register(item);

		if (item instanceof ItemModelProvider) ((ItemModelProvider)item).registerItemModel();
		if (item instanceof ItemOreDict) ((ItemOreDict)item).registerOreDict();
	}

}
