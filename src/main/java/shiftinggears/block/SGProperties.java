package shiftinggears.block;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import shiftinggears.material.EnumMaterial;

/**
 * @author shadowfacts
 */
public class SGProperties {

	public static final IProperty<EnumMaterial> ORE_MATERIAL = PropertyEnum.create("material", EnumMaterial.class, EnumMaterial::hasOre);
	public static final IProperty<EnumMaterial> MATERIAL = PropertyEnum.create("material", EnumMaterial.class);

}
