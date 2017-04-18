package shiftinggears.api.crafting;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

/**
 * @author shadowfacts
 */
public interface ICrucibleRecipe {

	FluidStack getOutput();

	boolean matches(List<ItemStack> inputs);

}
