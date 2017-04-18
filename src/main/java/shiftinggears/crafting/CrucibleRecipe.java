package shiftinggears.crafting;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import shiftinggears.api.crafting.ICrucibleRecipe;
import shiftinggears.util.Utils;

import java.util.Arrays;
import java.util.List;

/**
 * @author shadowfacts
 */
public class CrucibleRecipe implements ICrucibleRecipe {

	private FluidStack output;
	private List<ItemStack> inputs;

	public CrucibleRecipe(FluidStack output, ItemStack... inputs) {
		this.output = output;
		this.inputs = Arrays.asList(inputs);
	}

	@Override
	public FluidStack getOutput() {
		return output.copy();
	}

	@Override
	public boolean matches(List<ItemStack> inputs) {
		for (ItemStack stack : this.inputs) {
			if (!Utils.containsStack(inputs, stack)) {
				return false;
			}
		}
		return true;
	}

}
