package shiftinggears;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SoundManager {
	public static SoundEvent TABLE_HIT1;
	public static SoundEvent TABLE_HIT2;
	public static SoundEvent TABLE_HIT3;
	
	public static void init() {
		TABLE_HIT1 = registerSound("table1");
		TABLE_HIT2 = registerSound("table2");
		TABLE_HIT3 = registerSound("table3");
	}
	
	private static SoundEvent registerSound(String soundName) {
		final ResourceLocation soundID = new ResourceLocation(ShiftingGears.ID, soundName);
		return GameRegistry.register(new SoundEvent(soundID).setRegistryName(soundID));
	}
}
