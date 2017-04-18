package shiftinggears.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import java.lang.reflect.Field;

/**
 * @author shadowfacts
 */
public class SGFluids {

	public static FluidMoltenBrass moltenBrass = new FluidMoltenBrass();

	public static void init() {
		for (Field f : SGFluids.class.getDeclaredFields()) {
			if (Fluid.class.isAssignableFrom(f.getType())) {
				try {
					register((Fluid)f.get(null));
				} catch (ReflectiveOperationException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	private static void register(Fluid fluid) {
		FluidRegistry.registerFluid(fluid);
	}

}
