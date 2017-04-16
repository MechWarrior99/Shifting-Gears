package shiftinggears.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import shiftinggears.block.crank.TileEntityCrank;

/**
 * @author shadowfacts
 */
public class PacketRequestUpdateCrank implements IMessage {

	private int dimension;
	private BlockPos pos;

	public PacketRequestUpdateCrank(int dimension, BlockPos pos) {
		this.dimension = dimension;
		this.pos = pos;
	}

	public PacketRequestUpdateCrank(TileEntityCrank te) {
		this(te.getWorld().provider.getDimension(), te.getPos());
	}

	public PacketRequestUpdateCrank() {
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(dimension);
		buf.writeLong(pos.toLong());
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		dimension = buf.readInt();
		pos = BlockPos.fromLong(buf.readLong());
	}

	public static class Handler implements IMessageHandler<PacketRequestUpdateCrank, PacketUpdateCrank> {

		@Override
		public PacketUpdateCrank onMessage(PacketRequestUpdateCrank message, MessageContext ctx) {
			TileEntityCrank te = (TileEntityCrank)FMLCommonHandler.instance().getMinecraftServerInstance().worldServerForDimension(message.dimension).getTileEntity(message.pos);
			return new PacketUpdateCrank(te);
		}
	}

}
