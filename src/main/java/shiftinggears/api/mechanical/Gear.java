package shiftinggears.api.mechanical;


import shiftinggears.material.GearMaterial;

import java.util.List;

/**
 * @author ExpensiveKoala
 */
public class Gear {
    public MechSystem system;
    public boolean clockwise;
    public GearMaterial type;

    public IMechProvider connectedProvider;

    public float getSpeed() {
        return 0f;
    }

    public List<Gear> getConnectedGears() {
        return null;
    }

    public void initGear() {
        //Add self to MechSystem of neighbor gears and merge any that end up connected.
        //If no neighbor gears exist, create a new MechSystem and add self to it.
    }

    public void removeGear() {
        //Remove self from MechSystem and split MechSystem into two if MechSystem is no longer connected.
    }
}