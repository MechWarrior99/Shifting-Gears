package shiftinggears.block.crank;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import shiftinggears.api.mechanical.IMechanicalPowerObject;
import shiftinggears.tileentity.TEBase;

/**
 * @author shadowfacts
 */
public class TileEntityCrank extends TEBase implements IMechanicalPowerObject, ITickable {

	private static final double MAX_SPEED = 5;

	private double speed;
	float rotation;

	public void tryCrank() {
		speed += 1;
		if (speed > MAX_SPEED) speed = MAX_SPEED;
	}

	@Override
	public void update() {
		speed -= 0.05;
		if (speed < 0) speed = 0;

		if (speed > 0) transferPower();
	}

	private void transferPower() {
		TileEntity te = world.getTileEntity(pos.down());
		if (te instanceof IMechanicalPowerObject) {
			((IMechanicalPowerObject)te).apply(this);
		}
	}

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
