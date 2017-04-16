package shiftinggears.api.internal;

import shiftinggears.api.blueprint.IBlueprintManager;
import shiftinggears.api.crafting.ISGRecipeRegistry;

/**
 * @author shadowfacts
 */
public interface IInternalMethods {

	ISGRecipeRegistry getRecipeRegistry();

	IBlueprintManager getBlueprintManager();

}
