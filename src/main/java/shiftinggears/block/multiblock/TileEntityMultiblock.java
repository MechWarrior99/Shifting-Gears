package shiftinggears.block.multiblock;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import shiftinggears.tileentity.TEBase;

public abstract class TileEntityMultiblock extends TEBase implements ITickable{
    private boolean hasMain, isMain;
    private BlockPos mainPos;


    @Override
    public void update() {
        if(!world.isRemote) {
            updateHasMain();
            if(hasMain) {
                if (isMain && checkMultiblock()) {
                    updateMultiblock();
                }
            } else {
                //If for any reason one of the blocks isn't where they belong,
                // remove the multiblock structure.
                removeStructure();
            }
        }
    }

    protected void updateHasMain() {
        if(mainPos == null) {
            mainPos = getPos();
        }
        if(mainPos.equals(getPos()))
            isMain = true;
        if(world.getTileEntity(mainPos) == null) {
            hasMain = false;
            return;
        }
        if(world.getTileEntity(mainPos) instanceof TileEntityMultiblock)
            hasMain = true;
    }

    /**
     * Called to update the multiblock. Only called when multiblock is formed
     */
    public abstract void updateMultiblock();

    /**
     * Checks if Multiblock is still there.
     * @return true if all parts of the multiblock is still there
     */
    public abstract boolean checkMultiblock();

    /**
     * Called to remove the multiblock
     */
    public abstract void removeStructure();

    /**
     * Called to place blocks and create tile entities.
     */
    public void initMultiblock(boolean isMain, BlockPos mainPos) {
        setisMain(isMain);
        setMainPos(mainPos);
    }

    /**
     * Save any multiblock specific info
     * @param tag
     */
    public abstract void mainWriteToNBT(NBTTagCompound tag);

    /**
     * Read any saved multiblock specific info
     * @param tag
     */
    public abstract void mainReadFromNBT(NBTTagCompound tag);

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        mainPos = new BlockPos(compound.getInteger("mainX"), compound.getInteger("mainY"), compound.getInteger("mainZ"));
        hasMain = compound.getBoolean("hasMain");
        isMain = compound.getBoolean("isMain");
        if(hasMain && isMain)
            mainReadFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("mainX", mainPos.getX());
        compound.setInteger("mainY", mainPos.getY());
        compound.setInteger("mainZ", mainPos.getZ());
        compound.setBoolean("hasMain", hasMain);
        compound.setBoolean("isMain", isMain);
        if(hasMain && isMain)
            mainWriteToNBT(compound);
        return super.writeToNBT(compound);
    }

    @Override
    public boolean shouldRenderInPass(int pass) {
        return isMain();
    }

    public boolean isHasMain() {
        return hasMain;
    }

    public void setHasMain(boolean hasMain) {
        this.hasMain = hasMain;
    }

    public boolean isMain() {
        return isMain;
    }

    public void setisMain(boolean main) {
        isMain = main;
    }

    public BlockPos getMainPos() {
        return mainPos;
    }

    public void setMainPos(BlockPos mainPos) {
        this.mainPos = mainPos;
    }
}
