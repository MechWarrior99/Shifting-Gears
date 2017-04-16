package shiftinggears.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import shiftinggears.ShiftingGears;
import shiftinggears.block.crank.TileEntityCrank;

/**
 * @author shadowfacts
 */
public class PacketUpdateCrank implements IMessage {

	private BlockPos pos;
	private double speed;

	public PacketUpdateCrank(BlockPos pos, double speed) {
		this.pos = pos;
		this.speed = speed;
	}

	public PacketUpdateCrank(TileEntityCrank te) {
		this(te.getPos(), te.getSpeed());
	}

	public PacketUpdateCrank() {
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(pos.toLong());
		buf.writeDouble(speed);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		pos = BlockPos.fromLong(buf.readLong());
		speed = buf.readDouble();
	}

	public static class Handler implements IMessageHandler<PacketUpdateCrank, IMessage> {

		@Override
		public IMessage onMessage(PacketUpdateCrank message, MessageContext ctx) {
			TileEntityCrank te = (TileEntityCrank)ShiftingGears.proxy.getClientWorld().getTileEntity(message.pos);
			te.setSpeed(message.speed);
			return null;
		}

	}

}
