package shiftinggears.block.material;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import shiftinggears.ShiftingGears;
import shiftinggears.material.EnumMaterial;

/**
 * @author shadowfacts
 */
public class BlockOre extends Block {

	public static final IProperty<EnumMaterial> MATERIAL = PropertyEnum.create("material", EnumMaterial.class, EnumMaterial::hasOre);

	public BlockOre() {
		super(Material.ROCK);
		setRegistryName("ore");
		setUnlocalizedName(ShiftingGears.ID + ".ore");

		setDefaultState(getDefaultState().withProperty(MATERIAL, EnumMaterial.COPPER));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, MATERIAL);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(MATERIAL).ordinal();
	}

	@Override
	@Deprecated
	public IBlockState getStateFromMeta(int meta) {
		return EnumMaterial.values()[meta].getOreState();
	}

	@Override
	@SuppressWarnings("deprecated")
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		ItemStack stack = placer.getHeldItem(hand);
		return EnumMaterial.values()[stack.getMetadata()].getOreState();
	}

}
