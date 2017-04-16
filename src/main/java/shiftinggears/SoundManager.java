package shiftinggears;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class SoundManager {

	public static SoundEvent TABLE_HIT;

	public static void init() {
		TABLE_HIT = registerSound("table");
	}
	
	private static SoundEvent registerSound(String soundName) {
		final ResourceLocation soundID = new ResourceLocation(ShiftingGears.ID, soundName);
		return GameRegistry.register(new SoundEvent(soundID).setRegistryName(soundID));
	}

}
