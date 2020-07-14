package net.fabricmc.fabric.api.networking.v1;

import net.minecraft.network.listener.PacketListener;

@FunctionalInterface
public interface PacketListenerCallback<L extends PacketListener> {
	void handle(L handler);
}
