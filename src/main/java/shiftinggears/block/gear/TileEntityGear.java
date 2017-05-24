package shiftinggears.block.gear;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import shiftinggears.api.mechanical.Gear;

/**
 * @author ExpensiveKoala
 */
public class TileEntityGear extends TileEntity implements ITickable {

    Gear[] gears;

    public TileEntityGear() {
        gears = new Gear[6];
    }


    @Override
    public void update() {
        //Check the validity of each gear (if they can stay) and break if it can't
    }
}
