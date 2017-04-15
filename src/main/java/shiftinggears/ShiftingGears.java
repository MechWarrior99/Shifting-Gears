package shiftinggears;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import shiftinggears.proxy.AbstractProxy;

/**
 * @author shadowfacts
 */
@Mod(modid = ShiftingGears.ID, name = ShiftingGears.NAME, version = ShiftingGears.VERSION)
public class ShiftingGears {

	public static final String ID = "shiftinggears";
	public static final String NAME = "Shifting Gears";
	public static final String VERSION = "@VERSION@";

	@SidedProxy(serverSide = "shiftinggears.proxy.ServerProxy", clientSide = "shiftinggears.proxy.ClientProxy")
	public static AbstractProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {

	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {

	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

}
