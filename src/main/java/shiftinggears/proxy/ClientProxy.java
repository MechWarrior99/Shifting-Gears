package shiftinggears.proxy;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import shiftinggears.ShiftingGears;
import shiftinggears.tileentity.SpecialRenderCarpenter;
import shiftinggears.tileentity.TileEntityCarpenter;

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
	public void registerRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCarpenter.class, new SpecialRenderCarpenter());
	}

}
