package shiftinggears;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import shiftinggears.block.base.mechanical.BlockMechanical;
import shiftinggears.block.base.mechanical.TileEntityMechanical;

import javax.annotation.Nullable;

/**
 * @author shadowfacts
 */
@Mod(modid = "test")
public class TestMod {

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		GameRegistry.register(new BlockTest());
		GameRegistry.registerTileEntity(TileEntityTest.class, "test");
	}

	private static class BlockTest extends BlockMechanical<TileEntityTest> {

		public BlockTest() {
			super(Material.ROCK);
			setRegistryName("test");
		}

		@Nullable
		@Override
		public TileEntityTest createTileEntity(World world, IBlockState state) {
			return new TileEntityTest();
		}

		@Override
		public Class<TileEntityTest> getTEClass() {
			return TileEntityTest.class;
		}

	}

	private static class TileEntityTest extends TileEntityMechanical implements ITickable {

		private int i;
		private float rotation;

		@Override
		public void update() {
			if (world.isRemote) {
				rotation += speed;
				rotation %= 360;
				if (i++ % 20 == 0) {
					System.out.println(rotation);
				}
			}
		}

	}

}
