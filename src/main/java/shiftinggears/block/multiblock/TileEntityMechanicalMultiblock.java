package shiftinggears.block.multiblock;

import net.minecraft.nbt.NBTTagCompound;
import shiftinggears.api.mechanical.IMechanicalPowerBlock;

/**
 * @author ExpensiveKoala
 */
public abstract class TileEntityMechanicalMultiblock extends TileEntityMultiblock implements IMechanicalPowerBlock {

    double speed;

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        compound.setDouble("speed", speed);
        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setDouble("speed", speed);
        return super.writeToNBT(compound);
    }
}
