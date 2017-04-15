package shiftinggears;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import shiftinggears.block.SGBlocks;
import shiftinggears.crafting.RecipesHandler;
import shiftinggears.item.SGItems;
import shiftinggears.material.EnumMaterial;
import shiftinggears.proxy.AbstractProxy;
import shiftinggears.tileentity.SpecialRenderCarpenter;
import shiftinggears.tileentity.TileEntityCarpenter;
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
		File configDir = new File(event.getModConfigurationDirectory(), ID);
		if (!configDir.exists()) configDir.mkdirs();

		SGItems.init();
		SGBlocks.init();
		EnumMaterial.init();
		RecipesHandler.init();
		SoundManager.init();
		
		GameRegistry.registerTileEntity(TileEntityCarpenter.class, "carpenter");
		

		OreGenerator.init(new File(configDir, "ore-generation.json"));
		GameRegistry.registerWorldGenerator(OreGenerator.instance, 1);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCarpenter.class, new SpecialRenderCarpenter());
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}

}
