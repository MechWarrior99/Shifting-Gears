package shiftinggears.block.carpenter;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;

public class RendererCarpenter extends TileEntitySpecialRenderer<TileEntityCarpenter> {

	@Override
	public void renderTileEntityAt(TileEntityCarpenter te, double x, double y, double z, float partialTicks, int destroyStage){
		for(int i = 0; i < te.itemHeld.size(); i++){
			ItemStack stack = te.itemHeld.get(i);
			if(stack != ItemStack.EMPTY){
				EntityItem ei = new EntityItem(te.getWorld(), x, y, z, stack);
				ei.hoverStart = 0;

				GL11.glPushMatrix();
				RenderHelper.disableStandardItemLighting();
				GL11.glTranslated(x+te.x.get(i), y+1.05, z+te.z.get(i)-0.5);
				GL11.glRotatef(90, 1F, 0F, 0F);
				Minecraft.getMinecraft().getRenderManager().doRenderEntity(ei, 0, 0, 0, 0, 0, true);
				RenderHelper.enableStandardItemLighting();
				GL11.glPopMatrix();
			}
		}
	}

}
