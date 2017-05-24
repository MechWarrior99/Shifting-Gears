package shiftinggears.api.mechanical;

import net.minecraft.util.EnumFacing;

/**
 * @author ExpensiveKoala
 */
public interface IMechProvider {

    boolean getTurnDirection();

    EnumFacing getProvidingSide();

    float getProvidingSpeed();

    float getProvidingStrength();
}
