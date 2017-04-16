package shiftinggears.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import shiftinggears.ShiftingGears;
import shiftinggears.api.ShiftingGearsAPI;
import shiftinggears.blueprint.Blueprint;

import java.util.List;

/**
 * @author shadowfacts
 */
public class ItemBlueprint extends Item implements ItemModelProvider {

	public ItemBlueprint() {
		setRegistryName("blueprint");
		setUnlocalizedName(ShiftingGears.ID + ".blueprint");
		setHasSubtypes(true);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack heldItem = player.getHeldItem(hand);
		if (heldItem.hasTagCompound() && heldItem.getTagCompound().hasKey("blueprint", Constants.NBT.TAG_STRING)) {
			String id = heldItem.getTagCompound().getString("blueprint");
			Blueprint blueprint = ShiftingGearsAPI.getBlueprintManager().get(id);
			BlockPos placePos = pos.offset(facing);
			if (blueprint.canPlace(world, placePos, player.getHorizontalFacing()) && blueprint.meetsRequirements(player)) {
				blueprint.place(world, placePos, player.getHorizontalFacing());
				blueprint.removeItems(player);
				return EnumActionResult.SUCCESS;
			}
		}
		return EnumActionResult.FAIL;
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems) {
		ShiftingGearsAPI.getBlueprintManager().getBlueprints().forEach(id -> {
			ItemStack stack = new ItemStack(this);
			stack.setTagCompound(new NBTTagCompound());
			stack.getTagCompound().setString("blueprint", id);
			subItems.add(stack);
		});
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
		if (stack.hasTagCompound() && stack.getTagCompound().hasKey("blueprint", Constants.NBT.TAG_STRING)) {
			String id = stack.getTagCompound().getString("blueprint");
			tooltip.add("Blueprint: " + ShiftingGears.proxy.localize("shiftinggears.blueprint." + id));
			Blueprint blueprint = ShiftingGearsAPI.getBlueprintManager().get(id);
			blueprint.getRequirements().forEach(req -> {
				String name = ShiftingGears.proxy.localize(req.getUnlocalizedName() + ".name");
				tooltip.add("Requires: " + req.getCount() + "x " + name);
			});
		}
	}

	@Override
	public void registerItemModel() {
		ShiftingGears.proxy.registerItemModel(this, 0, "blueprint");
	}

}
