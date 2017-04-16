package shiftinggears.api;

import shiftinggears.api.blueprint.IBlueprintManager;
import shiftinggears.api.crafting.ISGRecipeRegistry;
import shiftinggears.api.internal.IInternalMethods;

/**
 * @author shadowfacts
 */
public class ShiftingGearsAPI {

	private static boolean initialized;
	private static IInternalMethods internal;

	public static void initialize(IInternalMethods internal) {
		ShiftingGearsAPI.internal = internal;
		initialized = true;
	}

	public static ISGRecipeRegistry getRecipeRegistry() {
		checkInitialized();
		return internal.getRecipeRegistry();
	}

	public static IBlueprintManager getBlueprintManager() {
		checkInitialized();
		return internal.getBlueprintManager();
	}

	private static void checkInitialized() {
		if (!initialized) throw new RuntimeException("Cannot call ShiftingGearsAPI methods before API initialization");
	}

}
