package shiftinggears.block.springloaded;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import shiftinggears.ShiftingGears;
import shiftinggears.block.multiblock.BlockMultiblock;

import javax.annotation.Nullable;

public class BlockSpringloaded extends BlockMultiblock<TileEntitySpringloaded> {

    public BlockSpringloaded() {
        super(Material.IRON);
        setRegistryName("springloaded");
        setUnlocalizedName(ShiftingGears.ID + ".springloaded");
    }

    @Nullable
    @Override
    public TileEntitySpringloaded createTileEntity(World world, IBlockState state) {
        return new TileEntitySpringloaded();
    }

    @Override
    public Class<TileEntitySpringloaded> getTEClass() {
        return TileEntitySpringloaded.class;
    }
}
