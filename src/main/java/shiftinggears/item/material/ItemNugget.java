package shiftinggears.item.material;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import shiftinggears.ShiftingGears;
import shiftinggears.material.EnumMaterial;

/**
 * @author shadowfacts
 */
public class ItemNugget extends Item {

	public ItemNugget() {
		setRegistryName("nugget");
		setUnlocalizedName(ShiftingGears.ID + ".nugget");
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "." + EnumMaterial.values()[stack.getMetadata()].getName();
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
		for (EnumMaterial mat : EnumMaterial.values()) {
			subItems.add(mat.getNugget());
		}
	}

}
