package shiftinggears.block.carpenter;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import shiftinggears.ShiftingGears;
import shiftinggears.SoundManager;
import shiftinggears.api.crafting.ICarpentersRecipe;
import shiftinggears.block.base.BlockTE;
import shiftinggears.crafting.RecipesHandler;
import shiftinggears.tileentity.TileEntityCarpenter;

import javax.annotation.Nullable;

public class BlockCarpenter extends BlockTE<TileEntityCarpenter> {

	private static final int HITS_UNTIL_DONE = 3;

	public BlockCarpenter(){
		super(Material.WOOD);
		setRegistryName("carpenter");
		setUnlocalizedName(ShiftingGears.ID + ".carpenter");
		setCreativeTab(CreativeTabs.MISC);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ){
        TileEntityCarpenter tc = getTileEntity(world, pos);
        if(!player.isSneaking()){
        	ItemStack heldItem = player.getHeldItem(hand);
        	if (facing == EnumFacing.UP && !heldItem.isEmpty()) {
            	tc.setItemHeld(heldItem.copy(), hitX, hitZ);
            }
        } else {
        	ArrayList<ItemStack> in = new ArrayList<ItemStack>();
        	for(ItemStack stack: tc.itemHeld){
        		if(stack != ItemStack.EMPTY){
        			in.add(stack);
        		}
        	}
        	ICarpentersRecipe r = RecipesHandler.INSTANCE.matchCarpentersRecipe(in);
			world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundManager.TABLE_HIT, SoundCategory.BLOCKS, 0.75F, 1F);
        	if(r != null){
        		tc.hit++;
        		if(tc.hit >= HITS_UNTIL_DONE){
        			if(!world.isRemote){
                		world.spawnEntity(new EntityItem(world, pos.getX(), pos.getY()+1, pos.getZ(), r.getOutput(in)));
                	}
            		tc.itemHeld.clear();
            		tc.hit = 0;
        		}
        	} else {
        		player.sendStatusMessage(new TextComponentString(TextFormatting.RED+"There's nothing to be made with what's here"), true);
        	}
        	
        }
		return true;
    }

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}

	@Nullable
	@Override
	public TileEntityCarpenter createTileEntity(World world, IBlockState state) {
		return new TileEntityCarpenter();
	}

	@Override
	public Class<TileEntityCarpenter> getTEClass() {
		return TileEntityCarpenter.class;
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
	@Deprecated
	public EnumBlockRenderType getRenderType(IBlockState state){
        return EnumBlockRenderType.MODEL;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer(){
        return BlockRenderLayer.CUTOUT;
    }

}
