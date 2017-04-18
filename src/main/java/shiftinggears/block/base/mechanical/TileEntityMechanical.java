package shiftinggears.block.base.mechanical;

import net.minecraft.nbt.NBTTagCompound;
import shiftinggears.api.mechanical.IMechanicalPowerBlock;
import shiftinggears.tileentity.TEBase;

/**
 * @author shadowfacts
 */
public class TileEntityMechanical extends TEBase implements IMechanicalPowerBlock {

	protected double speed;

	@Override
	public double getSpeed() {
		return speed;
	}

	@Override
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setDouble("speed", speed);
		return super.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		speed = compound.getDouble("speed");
		super.readFromNBT(compound);
	}

}
