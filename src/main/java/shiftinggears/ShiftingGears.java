package shiftinggears;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import shiftinggears.api.ShiftingGearsAPI;
import shiftinggears.block.SGBlocks;
import shiftinggears.crafting.CarpentersRecipe;
import shiftinggears.internal.InternalMethods;
import shiftinggears.item.SGItems;
import shiftinggears.material.EnumMaterial;
import shiftinggears.proxy.AbstractProxy;
import shiftinggears.world.ore.OreGenerator;

import java.io.File;

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
		initAPI();

		File configDir = new File(event.getModConfigurationDirectory(), ID);
		if (!configDir.exists()) configDir.mkdirs();

		SGItems.init();
		SGBlocks.init();
		EnumMaterial.init();
		SoundManager.init();

		// temp
		ShiftingGearsAPI.getRecipeRegistry().addCarpenterRecipe(new CarpentersRecipe(new ItemStack(Items.APPLE), new ItemStack(Items.DIAMOND), new ItemStack(Items.EMERALD)));

		OreGenerator.init(new File(configDir, "ore-generation.json"));
		GameRegistry.registerWorldGenerator(OreGenerator.instance, 1);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerRenderers();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

	private void initAPI() {
		ShiftingGearsAPI.initialize(new InternalMethods());
	}

}
