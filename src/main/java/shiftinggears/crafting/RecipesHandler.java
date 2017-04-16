package shiftinggears.crafting;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import shiftinggears.api.crafting.ICarpentersRecipe;
import shiftinggears.util.Util;

public class RecipesHandler {

	private static List<ICarpentersRecipe> carpenter = new ArrayList<>();
	
	public static void init(){
		addCarpenterRecipe(new CarpentersRecipe(new ItemStack(Items.APPLE), new ItemStack(Items.DIAMOND), new ItemStack(Items.EMERALD)));
	}
	
	public static void addCarpenterRecipe(ICarpentersRecipe r){
		carpenter.add(r);
	}
	
	public static ICarpentersRecipe matchCarpentersRecipe(List<ItemStack> inputs){
		for (ICarpentersRecipe recipe : carpenter) {
			if (recipe.matches(inputs)) {
				return recipe;
			}
		}
		return null;
	}

}
