package shiftinggears.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import shiftinggears.ShiftingGears;
import shiftinggears.blueprints.Blueprint;
import shiftinggears.blueprints.BlueprintManager;

import java.util.List;

/**
 *  @author ExpensiveKoala
 */
public class ItemBlueprint extends Item{

    public ItemBlueprint()
    {
        setRegistryName("blueprint");
        setUnlocalizedName(ShiftingGears.ID + ".blueprint");
        setHasSubtypes(true);
        setMaxStackSize(1);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(player.getHeldItem(hand).hasTagCompound()) {
            Blueprint print = getBlueprint(player.getHeldItem(hand).getTagCompound().getString("print"));
            if(BlueprintManager.hasIngredients(player, print)) {
                print.placeBlueprint(player, worldIn, pos, facing);
                BlueprintManager.removeIngredients(player, print);
                return EnumActionResult.SUCCESS;
            }
            return EnumActionResult.FAIL;
        }
        return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        if(stack.hasTagCompound()) {
            tooltip.add(stack.getTagCompound().getString("print"));
        }
        super.addInformation(stack, playerIn, tooltip, advanced);
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        for(String name : BlueprintManager.instance.prints) {
            ItemStack stack = new ItemStack(this);
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("print", name);
            stack.setTagCompound(tag);
            subItems.add(stack);
        }
    }

    public Blueprint getBlueprint(String multiblock) {
        for(Blueprint print :BlueprintManager.instance.blueprints) {
            if(print.multiblock.equals(multiblock)) {
                return print;
            }
        }
        return BlueprintManager.instance.blueprints.get(0);
    }
}
