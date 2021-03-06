package shiftinggears.block;

import java.lang.reflect.Field;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fml.common.registry.GameRegistry;
import shiftinggears.block.base.BlockTE;
import shiftinggears.block.crank.BlockCrank;
import shiftinggears.block.gear.BlockGear;
import shiftinggears.block.material.BlockBlock;
import shiftinggears.block.carpenter.BlockCarpenter;
import shiftinggears.block.material.BlockOre;
import shiftinggears.block.material.ItemBlockBlock;
import shiftinggears.block.material.ItemBlockOre;
import shiftinggears.block.springloaded.BlockSpringloaded;
import shiftinggears.fluid.SGFluids;
import shiftinggears.item.ItemGear;
import shiftinggears.item.ItemModelProvider;

/**
 * @author shadowfacts
 */
public class SGBlocks {

	@ItemBlock(ItemBlockOre.class)
	public static BlockOre ore = new BlockOre();
	@ItemBlock(ItemBlockBlock.class)
	public static BlockBlock block = new BlockBlock();
	public static BlockCarpenter carpenter = new BlockCarpenter();
	public static BlockCrank crank = new BlockCrank();
	public static BlockSpringloaded springloaded = new BlockSpringloaded();
	@ItemBlock.None
	public static BlockFluidClassic moltenBrass = new BlockFluidClassic(SGFluids.moltenBrass, Material.LAVA) {{
		setRegistryName("molten_brass");
	}};
	@ItemBlock(ItemGear.class)
	public static BlockGear gear = new BlockGear();

	public static void init() {
		for (Field f : SGBlocks.class.getFields()) {
			if (Block.class.isAssignableFrom(f.getType())) {
				try {
					Block block = (Block)f.get(null);
					register(block, createItemBlock(block, f));
				} catch (ReflectiveOperationException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	private static void register(Block block, net.minecraft.item.ItemBlock itemBlock) {
		GameRegistry.register(block);
		if (itemBlock != null) GameRegistry.register(itemBlock);

		if (block instanceof ItemModelProvider) ((ItemModelProvider)block).registerItemModel();
		if (block instanceof BlockTE) {
			GameRegistry.registerTileEntity(((BlockTE)block).getTEClass(), block.getRegistryName().toString());
		}
	}

	private static net.minecraft.item.ItemBlock createItemBlock(Block block, Field f) {
		if (f.isAnnotationPresent(ItemBlock.class)) {
			Class<? extends net.minecraft.item.ItemBlock> clazz = f.getAnnotation(ItemBlock.class).value();
			try {
				return clazz.getConstructor(Block.class).newInstance(block);
			} catch (ReflectiveOperationException e) {
				throw new RuntimeException(e);
			}
		} else if (f.isAnnotationPresent(ItemBlock.None.class)) {
			return null;
		} else {
			net.minecraft.item.ItemBlock itemBlock = new net.minecraft.item.ItemBlock(block);
			itemBlock.setRegistryName(block.getRegistryName());
			return itemBlock;
		}
	}

}
