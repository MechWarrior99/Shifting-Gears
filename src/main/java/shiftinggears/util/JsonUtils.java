package shiftinggears.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Map;

/**
 * @author shadowfacts
 */
public class JsonUtils {

	public static IBlockState deserializeState(JsonObject obj) throws JsonParseException {
		Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(obj.get("block").getAsString()));

		IBlockState state = block.getDefaultState();

		JsonObject props = obj.getAsJsonObject("properties");
		for (Map.Entry<String, JsonElement> e : props.entrySet()) {
			IProperty<?> prop = getProperty(state, e.getKey());
			state = withProperty(state, prop, prop.parseValue(e.getValue().getAsString()).get());
		}

		return state;
	}

	public static JsonObject serializeState(IBlockState src) {
		JsonObject obj = new JsonObject();

		obj.addProperty("block", src.getBlock().getRegistryName().toString());

		JsonObject props = new JsonObject();

		src.getProperties().forEach((prop, val) -> {
			props.addProperty(prop.getName(), getName(prop, val));
		});

		obj.add("properties", props);

		return obj;
	}

	private static IProperty<?> getProperty(IBlockState state, String name) {
		for (IProperty<?> prop : state.getPropertyKeys()) {
			if (prop.getName().equals(name)) {
				return prop;
			}
		}
		return null;
	}

	private static IBlockState withProperty(IBlockState state, IProperty prop, Comparable val) {
		return state.withProperty(prop, val);
	}

	private static String getName(IProperty prop, Comparable val) {
		return prop.getName(val);
	}

	public static NonNullList<ItemStack> deserializeItemStackList(JsonArray arr) {
		NonNullList<ItemStack> list = NonNullList.withSize(arr.size(), ItemStack.EMPTY);
		for (int i = 0; i < arr.size(); i++) {
			list.set(i, deserializeItemStack(arr.get(i).getAsJsonObject()));
		}
		return list;
	}

	public static ItemStack deserializeItemStack(JsonObject obj) {
		String id = obj.get("item").getAsString();
		int count = obj.has("count") ? obj.get("count").getAsInt() : 1;
		int meta = obj.has("meta") ? obj.get("meta").getAsInt() : 0;
		Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(id));
		return new ItemStack(item, count, meta);
	}

}
