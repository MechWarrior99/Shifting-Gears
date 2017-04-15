package shiftinggears.material;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import shiftinggears.ShiftingGears;
import shiftinggears.block.SGBlocks;
import shiftinggears.block.SGProperties;
import shiftinggears.item.SGItems;

/**
 * @author shadowfacts
 */
public enum EnumMaterial implements IStringSerializable {

	COPPER("Copper", true),
	ZINC("Zinc", true),
	BRASS("Brass", false);

	private String oreSuffix;
	private boolean hasOre;

	EnumMaterial(String oreSuffix, boolean hasOre) {
		this.oreSuffix = oreSuffix;
		this.hasOre = hasOre;
	}

	public String getOreSuffix() {
		return oreSuffix;
	}

	public boolean hasOre() {
		return hasOre;
	}

	@Override
	public String getName() {
		return name().toLowerCase();
	}

	public ItemStack getIngot() {
		return new ItemStack(SGItems.ingot, 1, ordinal());
	}

	public ItemStack getNugget() {
		return new ItemStack(SGItems.nugget, 1, ordinal());
	}

	public ItemStack getOre() {
		return new ItemStack(SGBlocks.ore, 1, ordinal());
	}

	public IBlockState getOreState() {
		return SGBlocks.ore.getDefaultState().withProperty(SGProperties.ORE_MATERIAL, this);
	}

	public ItemStack getBlock() {
		return new ItemStack(SGBlocks.block, 1, ordinal());
	}

	public IBlockState getBlockState() {
		return SGBlocks.block.getDefaultState().withProperty(SGProperties.MATERIAL, this);
	}

	public static void init() {
		for (EnumMaterial mat : values()) {
			ShiftingGears.proxy.registerItemModel(SGItems.ingot, mat.ordinal(), "ingot", "material=" + mat.getName());
			ShiftingGears.proxy.registerItemModel(SGItems.nugget, mat.ordinal(), "nugget", "material=" + mat.getName());
			ShiftingGears.proxy.registerItemModel(Item.getItemFromBlock(SGBlocks.block), mat.ordinal(), "block", "material=" + mat.getName());

			OreDictionary.registerOre("ingot" + mat.getOreSuffix(), mat.getIngot());
			OreDictionary.registerOre("nugget" + mat.getOreSuffix(), mat.getNugget());
			OreDictionary.registerOre("block" + mat.getOreSuffix(), mat.getBlock());

			GameRegistry.addShapelessRecipe(new ItemStack(SGItems.nugget, 9, mat.ordinal()), mat.getIngot());
			GameRegistry.addShapedRecipe(mat.getIngot(), "NNN", "NNN", "NNN", 'N', mat.getNugget());

			GameRegistry.addShapelessRecipe(new ItemStack(SGItems.ingot, 0, mat.ordinal()), mat.getBlock());
			GameRegistry.addShapedRecipe(mat.getBlock(), "III", "III", "III", 'I', mat.getIngot());

			if (mat.hasOre) {
				ShiftingGears.proxy.registerItemModel(Item.getItemFromBlock(SGBlocks.ore), mat.ordinal(), "ore", "material=" + mat.getName());

				OreDictionary.registerOre("ore" + mat.getOreSuffix(), mat.getOre());

				FurnaceRecipes.instance().addSmeltingRecipe(mat.getOre(), mat.getIngot(), 0.7f);
			}
		}
	}

}
