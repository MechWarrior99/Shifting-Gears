package shiftinggears.block.base.mechanical;

import net.minecraft.block.material.Material;
import shiftinggears.block.base.BlockTE;

/**
 * @author shadowfacts
 */
public abstract class BlockMechanical<TE extends TileEntityMechanical > extends BlockTE<TE> {

	public BlockMechanical(Material material) {
		super(material);
	}

}
