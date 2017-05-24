package shiftinggears.block.gear;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import shiftinggears.ShiftingGears;

/**
 * @author ExpensiveKoala
 */
public class GearRender extends TileEntitySpecialRenderer<TileEntityGear>{

    private static final GearModel MODEL = new GearModel();
    private static final ResourceLocation[] TEXTURES = {
            new ResourceLocation(ShiftingGears.ID, "textures/blocks/gear_wooden.png"),
            new ResourceLocation(ShiftingGears.ID, "textures/blocks/gear_brass.png")
    };

    @Override
    public void renderTileEntityAt(TileEntityGear te, double x, double y, double z, float partialTicks, int destroyStage) {
        super.renderTileEntityAt(te, x, y, z, partialTicks, destroyStage);
    }

}
