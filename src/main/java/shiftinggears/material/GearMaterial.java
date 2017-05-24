package shiftinggears.material;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import shiftinggears.block.SGBlocks;
import shiftinggears.block.SGProperties;

/**
 * @author ExpensiveKoala
 */
public enum GearMaterial implements IStringSerializable
{
    WOOD("woodenGear", 50F, 0),
    BRASS("brassGear", 100F, 1);

    private String name;
    private float maxSpeed;
    private int texture;
    GearMaterial(String name, float maxSpeed, int texture)
    {
        this.name = name;
        this.maxSpeed = maxSpeed;
        this.texture = texture;
    }

    public ItemStack getStack() {
        return new ItemStack(SGBlocks.gear, 1, ordinal());
    }

    public IBlockState getBlockState() {
        return SGBlocks.gear.getDefaultState().withProperty(SGProperties.GEAR_MATERIAL, this);
    }

    public float getMaxSpeed() {
        return this.maxSpeed;
    }

    public int getTexture() {
        return this.texture;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
