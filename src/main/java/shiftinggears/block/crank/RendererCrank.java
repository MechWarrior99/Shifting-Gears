package shiftinggears.block.crank;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import shiftinggears.ShiftingGears;

/**
 * @author shadowfacts
 */
public class RendererCrank extends TileEntitySpecialRenderer<TileEntityCrank> {

	private static final ModelCrank MODEL = new ModelCrank();
	private static final ResourceLocation TEXTURE = new ResourceLocation(ShiftingGears.ID, "textures/blocks/crank.png");

	@Override
	public void renderTileEntityAt(TileEntityCrank te, double x, double y, double z, float partialTicks, int destroyStage) {
		GlStateManager.pushMatrix();
		te.rotation += te.getSpeed();
		te.rotation %= 360;
		GlStateManager.translate(x + 8/16d, y + 1/16d, z + 8/16d);
		GlStateManager.rotate(180, 1, 0, 0);
		GlStateManager.rotate(te.rotation, 0, 1, 0);
		GlStateManager.translate(-2/16f, 0, -2/16f);
		Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
		MODEL.render(0.0625f);
		GlStateManager.popMatrix();
	}

}
