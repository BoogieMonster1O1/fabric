package net.fabricmc.fabric.api.networking.v1;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

public interface PacketSender {
	void sendPacket(Identifier channel, PacketByteBuf buf);
}
