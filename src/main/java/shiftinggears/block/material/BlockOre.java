package shiftinggears.block.material;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import shiftinggears.ShiftingGears;
import shiftinggears.block.SGProperties;
import shiftinggears.material.EnumMaterial;

/**
 * @author shadowfacts
 */
public class BlockOre extends Block {

	public BlockOre() {
		super(Material.ROCK);
		setRegistryName("ore");
		setUnlocalizedName(ShiftingGears.ID + ".ore");

		setDefaultState(getDefaultState().withProperty(SGProperties.ORE_MATERIAL, EnumMaterial.COPPER));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, SGProperties.ORE_MATERIAL);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(SGProperties.ORE_MATERIAL).ordinal();
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
