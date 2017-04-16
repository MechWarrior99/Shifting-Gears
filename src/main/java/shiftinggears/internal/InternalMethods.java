package shiftinggears.internal;

import shiftinggears.api.blueprint.IBlueprintManager;
import shiftinggears.api.crafting.ISGRecipeRegistry;
import shiftinggears.api.internal.IInternalMethods;
import shiftinggears.blueprint.BlueprintManager;
import shiftinggears.crafting.RecipesHandler;

/**
 * @author shadowfacts
 */
public class InternalMethods implements IInternalMethods {

	@Override
	public ISGRecipeRegistry getRecipeRegistry() {
		return RecipesHandler.INSTANCE;
	}

	@Override
	public IBlueprintManager getBlueprintManager() {
		return BlueprintManager.INSTANCE;
	}

}
