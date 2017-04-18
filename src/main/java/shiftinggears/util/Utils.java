package shiftinggears.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
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

	public static AxisAlignedBB rotate(AxisAlignedBB box, EnumFacing side) {
		switch (side) {
			case DOWN:
			default:
				return box;
			case UP:
				return new AxisAlignedBB(box.minX, 1 - box.maxY, box.minZ, box.maxX, 1 - box.minY, box.maxZ);
			case NORTH:
				return new AxisAlignedBB(box.minX, box.minZ, box.minY, box.maxX, box.maxZ, box.maxY);
			case SOUTH:
				return new AxisAlignedBB(box.minX, box.minZ, 1 - box.maxY, box.maxX, box.maxZ, 1 - box.minY);
			case WEST:
				return new AxisAlignedBB(box.minY, box.minZ, box.minX, box.maxY, box.maxZ, box.maxX);
			case EAST:
				return new AxisAlignedBB(1 - box.maxY, box.minZ, box.minX, 1 - box.minY, box.maxZ, box.maxX);
		}
	}

}
