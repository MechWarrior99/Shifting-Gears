package shiftinggears.tileentity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class SpecialRenderCarpenter extends TileEntitySpecialRenderer{
	@Override
	 public void renderTileEntityAt(TileEntity te, double x, double y, double z, float partialTicks, int destroyStage){
		 if(te instanceof TileEntityCarpenter){
			 TileEntityCarpenter stand = (TileEntityCarpenter) te;
			 for(int i = 0; i < stand.itemHeld.size(); i++){
				 ItemStack stack = stand.itemHeld.get(i);
				 if(stack != ItemStack.EMPTY){
					 EntityItem ei = new EntityItem(te.getWorld(), x, y, z, stack);
					 ei.hoverStart = 0;
					 
					 GL11.glPushMatrix();
					 RenderHelper.disableStandardItemLighting();
					 GL11.glTranslated(x+stand.x.get(i), y+1.05, z+stand.z.get(i)-0.5);
					 GL11.glRotatef(90, 1F, 0F, 0F);
					 Minecraft.getMinecraft().getRenderManager().doRenderEntity(ei, 0, 0, 0, 0, 0, true);
					 RenderHelper.enableStandardItemLighting();
					 GL11.glPopMatrix();	 
				 }
			 }
		 } 
	 }
}
