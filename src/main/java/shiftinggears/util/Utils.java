package shiftinggears.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraftforge.items.ItemHandlerHelper;

import java.util.List;

public class Utils {

	public static boolean containsStack(List<ItemStack> list, ItemStack stack) {
		for (ItemStack it : list) {
			if (ItemStack.areItemStacksEqual(it, stack)) {
				return true;
			}
		}
		return false;
	}

	public static ItemStack extractItem(IInventory inventory, int slot, int amount, boolean simulate) {
		if (amount == 0) {
			return ItemStack.EMPTY;
		}

		ItemStack existing = inventory.getStackInSlot(slot);

		if (existing.isEmpty()) {
			return ItemStack.EMPTY;
		}

		int toExtract = Math.min(amount, existing.getMaxStackSize());

		if (existing.getCount() <= toExtract) {
			if (!simulate) {
				inventory.setInventorySlotContents(slot, ItemStack.EMPTY);
			}
			return existing;
		} else {
			if (!simulate) {
				inventory.setInventorySlotContents(slot, ItemHandlerHelper.copyStackWithSize(existing, existing.getCount() - toExtract));
			}
			return ItemHandlerHelper.copyStackWithSize(existing, toExtract);
		}
	}

	public static Rotation getRotation(EnumFacing facing) {
		switch (facing) {
			case NORTH:
				return Rotation.CLOCKWISE_180;
			default:
			case SOUTH:
				return Rotation.NONE;
			case WEST:
				return Rotation.CLOCKWISE_90;
			case EAST:
				return Rotation.COUNTERCLOCKWISE_90;
		}
	}

}
