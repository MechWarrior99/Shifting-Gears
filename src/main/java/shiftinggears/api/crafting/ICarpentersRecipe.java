package shiftinggears.api.crafting;

import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * @author shadowfacts
 */
public interface ICarpentersRecipe {

	ItemStack getOutput();

	default ItemStack getOutput(List<ItemStack> inputs) {
		return getOutput();
	}

	boolean matches(List<ItemStack> inputs);

}
