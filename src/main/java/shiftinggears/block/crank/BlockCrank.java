package shiftinggears.block.crank;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import shiftinggears.ShiftingGears;
import shiftinggears.api.mechanical.IMechanicalPowerBlock;
import shiftinggears.block.SGProperties;
import shiftinggears.block.base.mechanical.BlockMechanical;
import shiftinggears.item.ItemModelProvider;
import shiftinggears.util.Utils;

import javax.annotation.Nullable;

/**
 * @author shadowfacts
 */
public class BlockCrank extends BlockMechanical<TileEntityCrank> implements ItemModelProvider {

	private static final AxisAlignedBB BOX = new AxisAlignedBB(3/16d, 0, 3/16d, 13/16d, 13/16d, 13/16d);
	private static final AxisAlignedBB[] BOXES = new AxisAlignedBB[6];

	static {
		for (EnumFacing facing : EnumFacing.VALUES) {
			BOXES[facing.ordinal()] = Utils.rotate(BOX, facing);
		}
	}

	public BlockCrank() {
		super(Material.WOOD);
		setRegistryName("crank");
		setUnlocalizedName(ShiftingGears.ID + ".crank");

		setDefaultState(blockState.getBaseState().withProperty(SGProperties.ORIENTATION, EnumFacing.DOWN));
	}

	@Override
	public void registerItemModel() {
		ShiftingGears.proxy.registerItemModel(Item.getItemFromBlock(this), 0, "crank");
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!(player instanceof FakePlayer)) {
			getTileEntity(world, pos).tryCrank();
			return true;
		} else {
			return false;
		}
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
		return getDefaultState().withProperty(SGProperties.ORIENTATION, facing.getOpposite());
	}

	@Override
	public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side) {
		BlockPos testPos = pos.offset(side.getOpposite());
		if (!world.getBlockState(testPos).isSideSolid(world, testPos, side)) return false;
		TileEntity te = world.getTileEntity(testPos);
//		return te != null && te instanceof IMechanicalPowerBlock;
		return true;
	}

	@Override
	@Deprecated
	public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
		if (!canPlaceBlockOnSide(world, pos, state.getValue(SGProperties.ORIENTATION).getOpposite())) {
			getDrops(world, pos, state, 0).forEach(stack -> world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack)));
			world.setBlockToAir(pos);
		}
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, SGProperties.ORIENTATION);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(SGProperties.ORIENTATION).ordinal();
	}

	@Override
	@Deprecated
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(SGProperties.ORIENTATION, EnumFacing.VALUES[meta]);
	}

	@Override
	@Deprecated
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BOXES[state.getValue(SGProperties.ORIENTATION).ordinal()];
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
