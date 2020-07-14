package net.fabricmc.fabric.api.networking.v1;

import java.util.Collection;

import net.minecraft.util.Identifier;

public interface PacketReceiver<C extends ListenerContext> extends ChannelAware {
	boolean register(Identifier channel, ChannelHandler<? super C> handler);

	ChannelHandler<? super C> unregister(Identifier channel);

	@Override
	Collection<Identifier> getChannels();

	@Override
	boolean hasChannel(Identifier channel);
}
