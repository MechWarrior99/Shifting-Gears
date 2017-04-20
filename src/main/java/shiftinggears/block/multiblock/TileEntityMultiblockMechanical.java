package shiftinggears.block.multiblock;

import net.minecraft.nbt.NBTTagCompound;
import shiftinggears.api.mechanical.IMechanicalPowerBlock;

public abstract class TileEntityMultiblockMechanical extends TileEntityMultiblock implements IMechanicalPowerBlock {

    double speed = 0;

    @Override
    public void update() {
        super.update();
        speed -= 0.05;
        if (speed < 0) speed = 0;
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(double speed) {
        if(isMain())
            this.speed = speed;
        else {
            if(getMainPos() != null) {
                TileEntityMultiblockMechanical tile = ((TileEntityMultiblockMechanical) getWorld().getTileEntity(getMainPos()));
                if (tile != null)
                    tile.setSpeed(speed);
            }
        }
    }


    @Override
    public void mainWriteToNBT(NBTTagCompound tag) {
        tag.setDouble("speed", speed);
    }

    @Override
    public void mainReadFromNBT(NBTTagCompound tag) {
        speed = tag.getDouble("speed");
    }
}
