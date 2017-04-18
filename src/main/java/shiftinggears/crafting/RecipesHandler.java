package shiftinggears.crafting;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import shiftinggears.api.crafting.ICarpentersRecipe;
import shiftinggears.api.crafting.ICrucibleRecipe;
import shiftinggears.api.crafting.ISGRecipeRegistry;

public class RecipesHandler implements ISGRecipeRegistry {

	public static final RecipesHandler INSTANCE = new RecipesHandler();

	private List<ICarpentersRecipe> carpenter = new ArrayList<>();
	private List<ICrucibleRecipe> crucible = new ArrayList<>();

	private RecipesHandler() {}

	@Override
	public void addCarpenterRecipe(ICarpentersRecipe recipe){
		carpenter.add(recipe);
	}

	@Override
	public void addCrucibleRecipe(ICrucibleRecipe recipe) {
		crucible.add(recipe);
	}

	public ICarpentersRecipe matchCarpentersRecipe(List<ItemStack> inputs){
		for (ICarpentersRecipe recipe : carpenter) {
			if (recipe.matches(inputs)) {
				return recipe;
			}
		}
		return null;
	}

	public ICrucibleRecipe matchCrucibleRecipe(List<ItemStack> inputs) {
		for (ICrucibleRecipe recipe : crucible) {
			if (recipe.matches(inputs)) {
				return recipe;
			}
		}
		return null;
	}

}
