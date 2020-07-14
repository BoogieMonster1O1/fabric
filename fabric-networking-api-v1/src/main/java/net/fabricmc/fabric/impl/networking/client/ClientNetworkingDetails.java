package net.fabricmc.fabric.impl.networking.client;

import net.minecraft.client.network.ClientLoginNetworkHandler;
import net.minecraft.client.network.ClientPlayNetworkHandler;

import net.fabricmc.fabric.api.networking.v1.client.ClientLoginContext;
import net.fabricmc.fabric.api.networking.v1.client.ClientPlayContext;
import net.fabricmc.fabric.impl.networking.BasicPacketReceiver;

public final class ClientNetworkingDetails {
	public static final BasicPacketReceiver<ClientLoginContext> LOGIN = new BasicPacketReceiver<>();
	public static final BasicPacketReceiver<ClientPlayContext> PLAY = new BasicPacketReceiver<>();

	public static ClientPlayNetworkAddon getAddon(ClientPlayNetworkHandler handler) {
		return ((ClientPlayNetworkHandlerHook) handler).getAddon();
	}

	public static ClientLoginNetworkAddon getAddon(ClientLoginNetworkHandler handler) {
		return ((ClientLoginNetworkHandlerHook) handler).getAddon();
	}
}
