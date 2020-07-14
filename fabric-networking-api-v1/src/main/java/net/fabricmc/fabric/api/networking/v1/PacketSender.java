package net.fabricmc.fabric.api.networking.v1;

import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

public interface PacketSender {
	void sendPacket(Identifier channel, PacketByteBuf buf);
}
