package shiftinggears.block.gear;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shiftinggears.ShiftingGears;
import shiftinggears.item.ItemModelProvider;
import shiftinggears.material.GearMaterial;
import shiftinggears.block.SGProperties;
import shiftinggears.block.base.BlockTE;

import javax.annotation.Nullable;

/**
 * @author ExpensiveKoala
 */
public class BlockGear extends BlockTE<TileEntityGear> implements ItemModelProvider{

    public BlockGear() {
        super(Material.IRON);
        setRegistryName("gear");
        setUnlocalizedName(ShiftingGears.ID + ".gear");

        setDefaultState(getDefaultState().withProperty(SGProperties.GEAR_MATERIAL, GearMaterial.BRASS));
    }

    @Override
    public void registerItemModel() {
        ShiftingGears.proxy.registerItemModel(Item.getItemFromBlock(this), 0, "gear");
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        //If shifting, find which gear was clicked on and pop it off
        return false;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        //Go through each Gear in the tile entity and drop the ItemGear associated with it.
        //REMEMBER to call the removeGear method in each gear
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, SGProperties.GEAR_MATERIAL);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(SGProperties.GEAR_MATERIAL).ordinal();
    }

    @Override
    @Deprecated
    public IBlockState getStateFromMeta(int meta) {
        return GearMaterial.values()[meta].getBlockState();
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntityGear createTileEntity(World world, IBlockState state) {
        return new TileEntityGear();
    }

    @Override
    public Class<TileEntityGear> getTEClass() {
        return TileEntityGear.class;
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
}
