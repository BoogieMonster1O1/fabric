package net.fabricmc.fabric.api.networking.v1;

import net.minecraft.util.PacketByteBuf;

@FunctionalInterface
public interface ChannelHandler<C extends ListenerContext> {
	void receive(C context, PacketByteBuf buf);

	@SuppressWarnings("unchecked")
	default <E extends Throwable> void rethrow(Throwable ex) throws E {
		throw (E) ex;
	}
}
