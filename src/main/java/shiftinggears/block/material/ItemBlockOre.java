package shiftinggears.block.material;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import shiftinggears.material.EnumMaterial;

/**
 * @author shadowfacts
 */
public class ItemBlockOre extends ItemBlock {

	public ItemBlockOre(Block block) {
		super(block);
		setRegistryName(block.getRegistryName());
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "." + EnumMaterial.values()[stack.getMetadata()].getName();
	}

	@Override
	public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems) {
		for (EnumMaterial mat : EnumMaterial.values()) {
			if (mat.hasOre()) subItems.add(mat.getOre());
		}
	}

}
