package shiftinggears.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import shiftinggears.ShiftingGears;
import shiftinggears.block.crank.RendererCrank;
import shiftinggears.block.crank.TileEntityCrank;
import shiftinggears.block.carpenter.RendererCarpenter;
import shiftinggears.block.carpenter.TileEntityCarpenter;
import shiftinggears.block.gear.GearRender;
import shiftinggears.block.gear.TileEntityGear;
import shiftinggears.event.BlueprintPreviewHandler;

/**
 * @author shadowfacts
 */
public class ClientProxy extends AbstractProxy {

	@Override
	public void registerItemModel(Item item, int meta, String name, String variant) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(ShiftingGears.ID, name), variant));
	}

	@Override
	public String localize(String key, Object... params) {
		return I18n.format(key, params);
	}

	@Override
	public World getClientWorld() {
		return Minecraft.getMinecraft().world;
	}

	@Override
	public void registerRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCarpenter.class, new RendererCarpenter());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrank.class, new RendererCrank());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGear.class, new GearRender());
	}

	@Override
	public void preInitClient() {
		MinecraftForge.EVENT_BUS.register(new BlueprintPreviewHandler());
	}
}
