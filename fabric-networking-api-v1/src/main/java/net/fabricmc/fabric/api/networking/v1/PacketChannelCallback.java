package net.fabricmc.fabric.api.networking.v1;

import java.util.List;

import net.minecraft.network.listener.PacketListener;
import net.minecraft.util.Identifier;

@FunctionalInterface
public interface PacketChannelCallback<L extends PacketListener> {
	void handle(L handler, List<Identifier> channels);
}
