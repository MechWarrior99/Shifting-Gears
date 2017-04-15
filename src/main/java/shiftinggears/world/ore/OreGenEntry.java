package shiftinggears.world.ore;

import com.google.gson.*;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.lang.reflect.Type;
import java.util.Map;

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

		private IBlockState deserializeState(JsonObject obj) throws JsonParseException {
			Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(obj.get("block").getAsString()));

			IBlockState state = block.getDefaultState();

			JsonObject props = obj.getAsJsonObject("properties");
			for (Map.Entry<String, JsonElement> e : props.entrySet()) {
				IProperty<?> prop = getProperty(state, e.getKey());
				state = withProperty(state, prop, prop.parseValue(e.getValue().getAsString()).get());
			}

			return state;
		}

		private JsonObject serializeState(IBlockState src) {
			JsonObject obj = new JsonObject();

			obj.addProperty("block", src.getBlock().getRegistryName().toString());

			JsonObject props = new JsonObject();

			src.getProperties().forEach((prop, val) -> {
				props.addProperty(prop.getName(), getName(prop, val));
			});

			obj.add("properties", props);

			return obj;
		}

		private IProperty<?> getProperty(IBlockState state, String name) {
			for (IProperty<?> prop : state.getPropertyKeys()) {
				if (prop.getName().equals(name)) {
					return prop;
				}
			}
			return null;
		}

		private IBlockState withProperty(IBlockState state, IProperty prop, Comparable val) {
			return state.withProperty(prop, val);
		}

		private String getName(IProperty prop, Comparable val) {
			return prop.getName(val);
		}

		@Override
		public OreGenEntry deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			JsonObject obj = json.getAsJsonObject();
			IBlockState state = deserializeState(obj.getAsJsonObject("block"));
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
			obj.add("block", serializeState(entry.state));
			obj.addProperty("dimension", entry.dimension);
			obj.addProperty("minY", entry.minY);
			obj.addProperty("maxY", entry.maxY);
			obj.addProperty("size", entry.size);
			obj.addProperty("chances", entry.chances);
			return obj;
		}

	}

}
