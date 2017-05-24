package shiftinggears.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import shiftinggears.ShiftingGears;
import shiftinggears.material.GearMaterial;

/**
 * @author ExpensiveKoala
 */
public class ItemGear extends ItemBlock implements ItemModelProvider {

    public ItemGear(Block block) {
        super(block);
        setRegistryName(block.getRegistryName());
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "." + GearMaterial.values()[stack.getMetadata()].getName();
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        for (GearMaterial mat : GearMaterial.values()) {
            subItems.add(mat.getStack());
        }
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        //Place Gear block if it isn't there.
        //Enable the gear on the side of the Gear block that the right click was on.
        return null;
    }

    @Override
    public void registerItemModel() {
        ShiftingGears.proxy.registerItemModel(this, 0, "gear");
    }
}
