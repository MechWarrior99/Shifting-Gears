package shiftinggears.crafting;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import shiftinggears.api.crafting.ICarpentersRecipe;
import shiftinggears.api.crafting.ISGRecipeRegistry;
import shiftinggears.util.Util;

public class RecipesHandler implements ISGRecipeRegistry {

	public static final RecipesHandler INSTANCE = new RecipesHandler();

	private List<ICarpentersRecipe> carpenter = new ArrayList<>();

	private RecipesHandler() {}

	@Override
	public void addCarpenterRecipe(ICarpentersRecipe r){
		carpenter.add(r);
	}
	
	public ICarpentersRecipe matchCarpentersRecipe(List<ItemStack> inputs){
		for (ICarpentersRecipe recipe : carpenter) {
			if (recipe.matches(inputs)) {
				return recipe;
			}
		}
		return null;
	}

}
