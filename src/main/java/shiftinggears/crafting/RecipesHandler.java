package shiftinggears.crafting;

import java.util.ArrayList;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import shiftinggears.util.Util;

public class RecipesHandler {
	public static ArrayList<CarpentersRecipe> carpenter = new ArrayList<CarpentersRecipe>();
	
	public static void init(){
		addCarpenterRecipe(new CarpentersRecipe(new ItemStack(Items.APPLE), new ItemStack(Items.DIAMOND), new ItemStack(Items.EMERALD)));
	}
	
	public static void addCarpenterRecipe(CarpentersRecipe r){
		carpenter.add(r);
	}
	
	public static CarpentersRecipe matchCarpentersRecipe(ArrayList<ItemStack> input){
		for(CarpentersRecipe recipe: carpenter){
			if(Util.matchLists(recipe.input, input)){
				return recipe;
			}
		}
		return null;
	}
}
