package shiftinggears.world.ore;

import com.google.gson.*;
import net.minecraft.block.state.IBlockState;
import shiftinggears.util.JsonUtils;

import java.lang.reflect.Type;

/**
 * @author shadowfacts
 */
public class OreGenEntry {

	public int dimension;
	public IBlockState state;
	public int minY;
	public int maxY;
	public int size;
	public int chances;

	public OreGenEntry(int dimension, IBlockState state, int minY, int maxY, int size, int chances) {
		this.dimension = dimension;
		this.state = state;
		this.minY = minY;
		this.maxY = maxY;
		this.size = size;
		this.chances = chances;
	}

	public static class Serializer implements JsonDeserializer<OreGenEntry>, JsonSerializer<OreGenEntry> {

		@Override
		public OreGenEntry deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			JsonObject obj = json.getAsJsonObject();
			IBlockState state = JsonUtils.deserializeState(obj.getAsJsonObject("block"));
			int dimension = obj.get("dimension").getAsInt();
			int minY = obj.get("minY").getAsInt();
			int maxY = obj.get("maxY").getAsInt();
			int size = obj.get("size").getAsInt();
			int chances = obj.get("chances").getAsInt();
			return new OreGenEntry(dimension, state, minY, maxY, size, chances);
		}

		@Override
		public JsonElement serialize(OreGenEntry entry, Type typeOfSrc, JsonSerializationContext context) {
			JsonObject obj = new JsonObject();
			obj.add("block", JsonUtils.serializeState(entry.state));
			obj.addProperty("dimension", entry.dimension);
			obj.addProperty("minY", entry.minY);
			obj.addProperty("maxY", entry.maxY);
			obj.addProperty("size", entry.size);
			obj.addProperty("chances", entry.chances);
			return obj;
		}

	}

}
