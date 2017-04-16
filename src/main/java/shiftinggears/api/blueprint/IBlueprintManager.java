package shiftinggears.api.blueprint;

import shiftinggears.blueprint.Blueprint;

import java.util.Set;

/**
 * @author shadowfacts
 */
public interface IBlueprintManager {

	void register(String id);

	Blueprint get(String id);

	Set<String> getBlueprints();

}
