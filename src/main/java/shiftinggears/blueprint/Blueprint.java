package shiftinggears.blueprint;

import com.google.gson.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import shiftinggears.util.JsonUtils;
import shiftinggears.util.Utils;

import java.lang.reflect.Type;

/**
 * @author shadowfacts
 */
public class Blueprint {

	private IBlockState[][][] multiblock;
	private NonNullList<ItemStack> requirements;

	public Blueprint(IBlockState[][][] multiblock, NonNullList<ItemStack> requirements) {
		this.multiblock = multiblock;
		this.requirements = requirements;
	}

	public boolean canPlace(World world, BlockPos pos, EnumFacing facing) {
		for (int y = 0; y < multiblock.length; y++) {
			for (int x = 0; x < multiblock[y].length; x++) {
				for (int z = 0; z < multiblock[y][x].length; z++) {
					BlockPos testPos = new BlockPos(x, y, z).rotate(Utils.getRotation(facing)).add(pos);
					if (!world.getBlockState(testPos).getBlock().isReplaceable(world, testPos)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public void place(World world, BlockPos pos, EnumFacing facing) {
		for (int y = 0; y < multiblock.length; y++) {
			for (int x = 0; x < multiblock[y].length; x++) {
				for (int z = 0; z < multiblock[y][x].length; z++) {
					BlockPos placePos = new BlockPos(x, y, z).rotate(Utils.getRotation(facing)).add(pos);
					world.setBlockState(placePos, multiblock[y][x][z]);
				}
			}
		}
	}

	public boolean meetsRequirements(EntityPlayer player) {
		InventoryPlayer inv = player.inventory;
		for (ItemStack req : requirements) {
			int amount = 0;
			for (int i = 0; i < inv.getSizeInventory(); i++) {
				ItemStack stack = inv.getStackInSlot(i);
				if (ItemStack.areItemsEqual(req, stack)) {
					amount += stack.getCount();
				}
			}
			if (amount < req.getCount()) return false;
		}
		return true;
	}

	public void removeItems(EntityPlayer player) {
		InventoryPlayer inv = player.inventory;
		for (ItemStack req : requirements) {
			int removed = 0;
			for (int i = 0; i < inv.getSizeInventory(); i++) {
				ItemStack stack = inv.getStackInSlot(i);
				if (ItemStack.areItemsEqual(req, stack)) {
					ItemStack extracted = Utils.extractItem(inv, i, req.getCount() - removed, false);
					removed += extracted.getCount();
					if (removed == req.getCount()) {
						break;
					}
				}
			}
		}
	}

	public NonNullList<ItemStack> getRequirements() {
		return requirements;
	}

	public static class Serializer implements JsonDeserializer<Blueprint> {

		@Override
		public Blueprint deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
			JsonObject obj = json.getAsJsonObject();
			IBlockState[][][] multiblock = deserializeMultiblock(obj.getAsJsonArray("multiblock"));
			NonNullList<ItemStack> inputs = JsonUtils.deserializeItemStackList(obj.getAsJsonArray("requirements"));
			return new Blueprint(multiblock, inputs);
		}

		private static IBlockState[][][] deserializeMultiblock(JsonArray arr) {
			IBlockState[][][] states = new IBlockState[arr.size()][][];
			for (int i = 0; i < arr.size(); i++) {
				JsonArray inner = arr.get(i).getAsJsonArray();
				IBlockState[][] innerStates = new IBlockState[inner.size()][];
				for (int j = 0; j < inner.size(); j++) {
					JsonArray innermost = inner.get(i).getAsJsonArray();
					IBlockState[] innermostStates = new IBlockState[innermost.size()];
					for (int k = 0; k < innermost.size(); k++) {
						innermostStates[k] = JsonUtils.deserializeState(innermost.get(k).getAsJsonObject());
					}
					innerStates[j] = innermostStates;
				}
				states[i] = innerStates;
			}
			return states;
		}

	}

}
