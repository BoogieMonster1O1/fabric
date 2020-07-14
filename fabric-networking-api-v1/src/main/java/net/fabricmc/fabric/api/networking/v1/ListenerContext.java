package net.fabricmc.fabric.api.networking.v1;

import net.minecraft.network.listener.PacketListener;
import net.minecraft.util.GameEngine;

public interface ListenerContext {
	PacketListener getListener();

	GameEngine getEngine();
}
