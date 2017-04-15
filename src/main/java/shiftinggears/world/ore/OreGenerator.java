package shiftinggears.world.ore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;
import org.apache.commons.io.FileUtils;
import shiftinggears.material.EnumMaterial;

import java.io.*;
import java.util.Random;

/**
 * @author shadowfacts
 */
public class OreGenerator implements IWorldGenerator {

	private static final Gson GSON = new GsonBuilder()
			.registerTypeAdapter(OreGenEntry.class, new OreGenEntry.Serializer())
			.setPrettyPrinting()
			.create();

	public static OreGenerator instance;

	private OreGenEntry[] entries;

	public static void init(File f) {
		try {
			if (!f.exists()) {
				instance = new OreGenerator();
				instance.entries = new OreGenEntry[2];
				instance.entries[0] = new OreGenEntry(0, EnumMaterial.COPPER.getOreState(), 16, 60, 8, 4);
				instance.entries[1] = new OreGenEntry(0, EnumMaterial.ZINC.getOreState(), 45, 85, 6, 2);
				f.createNewFile();
			} else {
				instance = GSON.fromJson(new FileReader(f), OreGenerator.class);
			}
			FileUtils.write(f, GSON.toJson(instance));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		for (OreGenEntry e : entries) {
			if (e.dimension == world.provider.getDimension()) {
				generateOre(e.state, world, random, chunkX * 16, chunkZ * 16, e.minY, e.maxY, e.size, e.chances);
			}
		}
	}

	private void generateOre(IBlockState state, World world, Random random, int x, int z, int minY, int maxY, int size, int chances) {
		int deltaY = maxY - minY;

		for (int i = 0; i < chances; i++) {
			BlockPos pos = new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), x + random.nextInt(16));

			WorldGenMinable generator = new WorldGenMinable(state, size);
			generator.generate(world, random, pos);
		}
	}

}
