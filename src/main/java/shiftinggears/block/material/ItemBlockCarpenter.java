package shiftinggears.block.material;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import shiftinggears.material.EnumMaterial;

public class ItemBlockCarpenter extends ItemBlock {

	public ItemBlockCarpenter(Block block) {
		super(block);
		setRegistryName(block.getRegistryName());
		setHasSubtypes(false);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "." + EnumMaterial.values()[stack.getMetadata()].getName();
	}

}
