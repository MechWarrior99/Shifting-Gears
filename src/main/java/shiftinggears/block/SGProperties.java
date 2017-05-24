package shiftinggears.block;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.EnumFacing;
import shiftinggears.material.GearMaterial;
import shiftinggears.material.EnumMaterial;

/**
 * @author shadowfacts
 */
public class SGProperties {

//	Materials
	public static final IProperty<EnumMaterial> ORE_MATERIAL = PropertyEnum.create("material", EnumMaterial.class, EnumMaterial::hasOre);
	public static final IProperty<EnumMaterial> MATERIAL = PropertyEnum.create("material", EnumMaterial.class);

//	Crank
	public static final IProperty<EnumFacing> ORIENTATION = PropertyDirection.create("orientation");

//  Gear
    public static final IProperty<GearMaterial> GEAR_MATERIAL = PropertyEnum.create("gear_material", GearMaterial.class);

}
