package shiftinggears.block.multiblock;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import shiftinggears.block.base.BlockTE;

import javax.annotation.Nullable;

public abstract class BlockMultiblock<TE extends TileEntityMultiblock> extends BlockTE<TE>{

    public BlockMultiblock(Material material) {
        super(material);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        if(hasTileEntity(state) && worldIn.getTileEntity(pos) instanceof TileEntityMultiblock)
            ((TileEntityMultiblock)worldIn.getTileEntity(pos)).removeStructure();
        super.breakBlock(worldIn, pos, state);
    }
}
