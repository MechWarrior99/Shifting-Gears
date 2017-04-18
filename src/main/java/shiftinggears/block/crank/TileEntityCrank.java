package shiftinggears.block.crank;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import shiftinggears.ShiftingGears;
import shiftinggears.api.mechanical.IMechanicalPowerBlock;
import shiftinggears.block.SGProperties;
import shiftinggears.block.base.mechanical.TileEntityMechanical;
import shiftinggears.network.PacketRequestUpdateCrank;

/**
 * @author shadowfacts
 */
public class TileEntityCrank extends TileEntityMechanical implements ITickable {

	private static final double MAX_SPEED = 5;

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
		world.getChunkFromBlockCoords(pos).setChunkModified();
	}

	private void transferPower() {
		TileEntity te = world.getTileEntity(pos.offset(world.getBlockState(pos).getValue(SGProperties.ORIENTATION)));
		if (te instanceof IMechanicalPowerBlock) {
			((IMechanicalPowerBlock)te).setSpeed(speed);
		}
	}

	@Override
	public void onLoad() {
		if (world.isRemote) {
			ShiftingGears.network.sendToServer(new PacketRequestUpdateCrank(this));
		}
	}

}
