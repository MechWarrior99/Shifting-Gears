package shiftinggears.internal;

import shiftinggears.api.crafting.ISGRecipeRegistry;
import shiftinggears.api.internal.IInternalMethods;
import shiftinggears.crafting.RecipesHandler;

/**
 * @author shadowfacts
 */
public class InternalMethods implements IInternalMethods {

	@Override
	public ISGRecipeRegistry getRecipeRegistry() {
		return RecipesHandler.INSTANCE;
	}

}
