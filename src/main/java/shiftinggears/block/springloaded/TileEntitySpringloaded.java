package shiftinggears.block.springloaded;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import shiftinggears.block.multiblock.TileEntityMultiblockMechanical;

public class TileEntitySpringloaded extends TileEntityMultiblockMechanical {

    public final double MAX_PROGRESS = 100;

    public double progress = 0;

    @Override
    public void updateMultiblock() {
        if(MAX_PROGRESS > progress)
            progress += getSpeed();
        else {
            smashBlock();
            progress = 0;
        }
        setSpeed(0);
    }

    private void smashBlock() {
        //TODO:
        System.out.println("Do stuff!");
    }

    @Override
    public boolean checkMultiblock() {
        //TODO:
        return true;
    }

    @Override
    public void removeStructure() {
        //TODO:
    }

    @Override
    public void mainWriteToNBT(NBTTagCompound tag) {
        super.mainWriteToNBT(tag);
        tag.setDouble("progress", progress);
    }

    @Override
    public void mainReadFromNBT(NBTTagCompound tag) {
        super.mainReadFromNBT(tag);
        progress = tag.getDouble("progress");
    }

}
