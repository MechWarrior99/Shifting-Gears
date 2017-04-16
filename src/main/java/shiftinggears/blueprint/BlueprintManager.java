package shiftinggears.blueprint;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import shiftinggears.ShiftingGears;
import shiftinggears.api.blueprint.IBlueprintManager;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author shadowfacts
 */
public class BlueprintManager implements IBlueprintManager {

	public static final BlueprintManager INSTANCE = new BlueprintManager();

	private Gson gson = new GsonBuilder()
			.registerTypeAdapter(Blueprint.class, new Blueprint.Serializer())
			.create();

	private Map<String, Blueprint> blueprints = new HashMap<>();

	private BlueprintManager() {}

	private Blueprint load(String id) {
		InputStream in = BlueprintManager.class.getResourceAsStream("/assets/" + ShiftingGears.ID + "/blueprints/" + id + ".json");
		return gson.fromJson(new InputStreamReader(in), Blueprint.class);
	}

	@Override
	public void register(String id) {
		blueprints.put(id, load(id));
	}

	@Override
	public Blueprint get(String id) {
		return blueprints.get(id);
	}

	@Override
	public Set<String> getBlueprints() {
		return blueprints.keySet();
	}

}
