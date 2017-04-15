package shiftinggears.crafting;

import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.item.ItemStack;

public class CarpentersRecipe {
	public ArrayList<ItemStack> input = new ArrayList<ItemStack>();
	public ItemStack output;
	
	public CarpentersRecipe(ItemStack output, ItemStack...input){
		for(ItemStack in: input){
			this.input.add(in);
		}
		this.output = output;
	}
}
