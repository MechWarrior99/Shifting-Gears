package shiftinggears.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * @author shadowfacts
 */
public abstract class BlockTE<TE extends TileEntity> extends Block {

	public BlockTE(Material material) {
		super(material);
	}

	public BlockTE() {
		this(Material.ROCK);
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Nullable
	@Override
	public abstract TE createTileEntity(World world, IBlockState state);

	public abstract Class<TE> getTEClass();

	public TE getTileEntity(IBlockAccess world, BlockPos pos) {
		return (TE)world.getTileEntity(pos);
	}

}
