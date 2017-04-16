package shiftinggears.event;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.model.pipeline.LightUtil;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;
import shiftinggears.blueprint.Blueprint;
import shiftinggears.item.ItemBlueprint;
import shiftinggears.item.SGItems;
import shiftinggears.util.Utils;

import java.util.List;

/**
 * @author shadowfacts
 */
public class BlueprintPreviewHandler {

	@SubscribeEvent
	public void onRenderWorldLast(RenderWorldLastEvent event) {
		EntityPlayer player = Minecraft.getMinecraft().player;
		ItemStack main = player.getHeldItem(EnumHand.MAIN_HAND);
		if (main.getItem() == SGItems.blueprint) {
			Blueprint blueprint = ItemBlueprint.getBlueprint(main);
			if (blueprint != null) renderBlueprint(blueprint, event.getPartialTicks());
		} else {
			ItemStack off = player.getHeldItem(EnumHand.OFF_HAND);
			if (off.getItem() == SGItems.blueprint) {
				Blueprint blueprint = ItemBlueprint.getBlueprint(off);
				if (blueprint != null) renderBlueprint(blueprint, event.getPartialTicks());
			}
		}
	}

	private static void renderBlueprint(Blueprint blueprint, float partialTicks) {
		World world = Minecraft.getMinecraft().world;
		EntityPlayer player = Minecraft.getMinecraft().player;
		RayTraceResult hit = Minecraft.getMinecraft().objectMouseOver;
		BlockPos pos = hit.getBlockPos().offset(hit.sideHit);
		EnumFacing facing = player.getHorizontalFacing();
		if (hit.typeOfHit == RayTraceResult.Type.BLOCK && blueprint.canPlace(world, pos, facing)) {
			GlStateManager.pushMatrix();

			translate(player, partialTicks);

			GlStateManager.enableBlend();
			GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			int color = 0x9f000000;
			if (!blueprint.meetsRequirements(player)) color |= 0x00ff5555;
			else color |= 0xffffff;

			VertexBuffer buf = Tessellator.getInstance().getBuffer();
			buf.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);

			IBlockState[][][] multiblock = blueprint.getMultiblock();
			for (int y = 0; y < multiblock.length; y++) {
				for (int x = 0; x < multiblock[y].length; x++) {
					for (int z = 0; z < multiblock[y][x].length; z++) {
						BlockPos statePos = new BlockPos(x, y, z).rotate(Utils.getRotation(facing)).add(pos);
						renderState(multiblock[y][x][z], statePos, buf, color);
					}
				}
			}

			Tessellator.getInstance().draw();

			GlStateManager.disableBlend();

			GlStateManager.popMatrix();
		}
	}

	private static void translate(EntityPlayer player, float partialTicks) {
		double x = -(player.lastTickPosX + (player.posX - player.lastTickPosX) * partialTicks);
		double y = -(player.lastTickPosY + (player.posY - player.lastTickPosY) * partialTicks);
		double z = -(player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * partialTicks);
		GlStateManager.translate(x, y, z);
	}

	private static void renderState(IBlockState state, BlockPos pos, VertexBuffer buf, int color) {
		World world = Minecraft.getMinecraft().world;
		IBakedModel model = Minecraft.getMinecraft().getBlockRendererDispatcher().getModelForState(state);

		long rand = MathHelper.getPositionRandom(pos);

		for (EnumFacing facing : EnumFacing.values()) {
			List<BakedQuad> quads = model.getQuads(state, facing, rand);
			if (!quads.isEmpty()) {
				int i = state.getPackedLightmapCoords(world, pos.offset(facing));
				renderQuads(world, state, pos, i, buf, quads, color);
			}
		}

		List<BakedQuad> quads = model.getQuads(state, null, rand);
		if (!quads.isEmpty()) {
			int i = state.getPackedLightmapCoords(world, pos);
			renderQuads(world, state, pos, i, buf, quads, color);
		}
	}

	private static void renderQuads(World world, IBlockState state, BlockPos pos, int brightness, VertexBuffer buf, List<BakedQuad> quads, int color) {
		Vec3d offset = state.getOffset(world, pos);
		double x = pos.getX() + offset.xCoord;
		double y = pos.getY() + offset.yCoord;
		double z = pos.getZ() + offset.zCoord;

		int i = 0;
		for (int j = quads.size(); i < j; ++i) {
			BakedQuad quad = quads.get(i);

			buf.addVertexData(quad.getVertexData());
			buf.putBrightness4(brightness, brightness, brightness, brightness);

			int mergedColor = color;
			if (quad.hasTintIndex()) mergedColor |= Minecraft.getMinecraft().getBlockColors().colorMultiplier(state, world, pos, quad.getTintIndex());

			LightUtil.renderQuadColor(buf, quad, mergedColor);
			buf.putPosition(x, y, z);
		}
	}

}
