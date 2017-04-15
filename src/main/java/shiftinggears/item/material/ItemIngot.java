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
public class ItemIngot extends Item {

	public ItemIngot() {
		setRegistryName("ingot");
		setUnlocalizedName(ShiftingGears.ID + ".ingot");
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "." + EnumMaterial.values()[stack.getMetadata()].getName();
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems) {
		for (EnumMaterial mat : EnumMaterial.values()) {
			subItems.add(mat.getIngot());
		}
	}

}
