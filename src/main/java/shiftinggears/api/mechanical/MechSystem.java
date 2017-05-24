package shiftinggears.api.mechanical;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ExpensiveKoala
 */
public class MechSystem {
    private float speed;

    private List<Gear> gears;

    public MechSystem(Gear gear)
    {
        addGear(gear);
        calculateSpeed();
    }

    public void addGear(Gear gear)
    {
        if(gears == null)
            gears = new ArrayList<>();
        gears.add(gear);
    }

    public void calculateSpeed()
    {
        //Need to come up with formula for getting speed from strength and alternate +/-
    }

    public void merge(MechSystem system)
    {
        gears.addAll(system.gears);
    }

    public void split()
    {
        //Look for and split MechSystems that aren't connected.
    }
}
