package shiftinggears.tileentity;

import java.util.ArrayList;

import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.util.Constants;

public class TileEntityCarpenter extends TEBase{
	public NonNullList<ItemStack> itemHeld = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
	
	public ArrayList<Double> x = new ArrayList<Double>();
	public ArrayList<Double> z = new ArrayList<Double>();
	
	public int hit = 0;
	
	public void setItemHeld(ItemStack stack, double x, double z){
			for(int i = 0; i < itemHeld.size(); i++){
				if(itemHeld.get(i) == ItemStack.EMPTY){
					itemHeld.set(i, stack);
					this.x.add(x);
					this.z.add(z);
					break;
				}
			}
			
		this.markDirty();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound){
		super.readFromNBT(compound);
		this.itemHeld = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.itemHeld);
		
		NBTTagList tagList = compound.getTagList("xl", Constants.NBT.TAG_COMPOUND);
        for(int i = 0; i < tagList.tagCount(); i++){
         NBTTagCompound tag = tagList.getCompoundTagAt(i);
         double s = tag.getDouble("x" + i);
         x.add(i, s);
        }
        
        NBTTagList tagList2 = compound.getTagList("zl", Constants.NBT.TAG_COMPOUND);
        for(int i = 0; i < tagList2.tagCount(); i++){
         NBTTagCompound tag = tagList2.getCompoundTagAt(i);
         double s = tag.getDouble("z" + i);
         z.add(i, s);
        }
    }

	@Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);
        ItemStackHelper.saveAllItems(compound, this.itemHeld);
        
        NBTTagList tagList = new NBTTagList();
		for(int i = 0; i < x.size(); i++){
		  double s = x.get(i);
		  NBTTagCompound tag = new NBTTagCompound();
		  tag.setDouble("x" + i, s);
		  tagList.appendTag(tag);
		}
		compound.setTag("xl", tagList);
		
		NBTTagList tagList2 = new NBTTagList();
		for(int i = 0; i < z.size(); i++){
			  double s = z.get(i);
			  NBTTagCompound tag = new NBTTagCompound();
			  tag.setDouble("z" + i, s);
			  tagList2.appendTag(tag);
			}
		compound.setTag("zl", tagList2);
		
        return compound;
    }
}
