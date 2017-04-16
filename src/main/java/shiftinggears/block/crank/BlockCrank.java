package shiftinggears.block.crank;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import shiftinggears.ShiftingGears;
import shiftinggears.api.mechanical.IMechanicalPowerObject;
import shiftinggears.block.base.BlockTE;

import javax.annotation.Nullable;

/**
 * @author shadowfacts
 */
public class BlockCrank extends BlockTE<TileEntityCrank> {

	public BlockCrank() {
		super(Material.WOOD);
		setRegistryName("crank");
		setUnlocalizedName(ShiftingGears.ID + ".crank");
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		getTileEntity(world, pos).tryCrank();
		return true;
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side) {
		if (side != EnumFacing.UP) return false;
		if (!world.getBlockState(pos.down()).isSideSolid(world, pos.down(), EnumFacing.UP)) return false;
		TileEntity te = world.getTileEntity(pos.down());
		return te != null && te instanceof IMechanicalPowerObject;
	}

	@Override
	@Deprecated
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (!canPlaceBlockOnSide(world, pos, EnumFacing.UP)) {
			getDrops(world, pos, state, 0).forEach(stack -> world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack)));
			world.setBlockToAir(pos);
		}
	}

	@Override
	@Deprecated
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.INVISIBLE;
	}

	@Override
	@Deprecated
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	@Deprecated
	public boolean isFullCube(IBlockState state){
		return false;
	}

	@Override
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return false;
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntityCrank createTileEntity(World world, IBlockState state) {
		return new TileEntityCrank();
	}

	@Override
	public Class<TileEntityCrank> getTEClass() {
		return TileEntityCrank.class;
	}
}
