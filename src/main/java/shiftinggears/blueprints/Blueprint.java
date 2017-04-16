package shiftinggears.blueprints;

import com.google.gson.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ExpensiveKoala
 */
public class Blueprint {

    public String multiblock;
    public List<ItemStack> inputs;

    public Blueprint(String multiblock, List<ItemStack> inputs) {
        this.multiblock = multiblock;
        this.inputs = inputs;
    }

    public void placeBlueprint(EntityPlayer player, World world, BlockPos pos, EnumFacing facing) {
        //ToDo: Place & form multiblock
    }

    public static class Serializer implements JsonDeserializer<Blueprint>, JsonSerializer<Blueprint> {

        public List<ItemStack> deserializeInputs(JsonObject obj) throws JsonParseException {
            JsonArray arr = obj.getAsJsonArray("inputs");
            Pattern pattern = Pattern.compile("(.*?)@(\\d+)#(\\d+)");
            List<ItemStack> items = new ArrayList<>();
            for (JsonElement el : arr) {
                String s = el.getAsString();
                Matcher match = pattern.matcher(s);
                String id = match.group(1);
                Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(id));
                int meta = Integer.parseInt(match.group(2));
                int count = Integer.parseInt(match.group(3));
                items.add(new ItemStack(item, meta, count));
            }
            return items;
        }

        public JsonObject serializeInputs(List<ItemStack> inputs) {
            JsonObject obj = new JsonObject();
            JsonArray arr = new JsonArray();
            for (ItemStack stack : inputs) {
                String s = String.format("%s@%d#%d", stack.getItem().getRegistryName(), stack.getMetadata(), stack.getCount());
                arr.add(new JsonPrimitive(s));
            }
            obj.add("inputs", arr);
            return obj;
        }

        @Override
        public Blueprint deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject obj = json.getAsJsonObject();
            String mbName = obj.get("name").getAsString();
            List<ItemStack> inputs = deserializeInputs(obj.getAsJsonObject("inputs"));
            return new Blueprint(mbName, inputs);
        }

        @Override
        public JsonElement serialize(Blueprint src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject obj = new JsonObject();
            obj.addProperty("name", src.multiblock);
            obj.add("inputs", serializeInputs(src.inputs));
            return obj;
        }
    }
}
