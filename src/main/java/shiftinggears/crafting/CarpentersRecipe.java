package shiftinggears.crafting;

import java.util.Arrays;
import java.util.List;

import net.minecraft.item.ItemStack;
import shiftinggears.api.crafting.ICarpentersRecipe;
import shiftinggears.util.Utils;

public class CarpentersRecipe implements ICarpentersRecipe {

	private List<ItemStack> inputs;
	private ItemStack output;
	
	public CarpentersRecipe(ItemStack output, ItemStack...input){
		this.inputs = Arrays.asList(input);
		this.output = output;
	}

	@Override
	public ItemStack getOutput() {
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
