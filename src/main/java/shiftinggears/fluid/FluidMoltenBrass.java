package shiftinggears.fluid;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import shiftinggears.ShiftingGears;

/**
 * @author shadowfacts
 */
public class FluidMoltenBrass extends Fluid {

	private static final ResourceLocation STILL_TEX = new ResourceLocation(ShiftingGears.ID, "fluids/molten_brass/still");
	private static final ResourceLocation FLOWING_TEX = new ResourceLocation(ShiftingGears.ID, "fluids/molten_brass/flowing");

	public FluidMoltenBrass() {
		super("molten_brass", STILL_TEX, FLOWING_TEX);
		setViscosity(6000);
		setTemperature(2000);
	}

}
