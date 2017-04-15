package shiftinggears.util;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class Util {
	public static boolean matchLists(ArrayList<ItemStack> list1, ArrayList<ItemStack> list2){
		if(list1.size() == list2.size()){
			for(int i = 0; i < list1.size(); i++){
				for(int i2 = 0; i2 < list2.size(); i2++){
					if(ItemStack.areItemStacksEqual(list1.get(i), list2.get(i2)) ){
							list2.remove(i2);
					}
				}
			}
		} else {
			return false;
		}
		return list2.size() == 0;
	}
}
