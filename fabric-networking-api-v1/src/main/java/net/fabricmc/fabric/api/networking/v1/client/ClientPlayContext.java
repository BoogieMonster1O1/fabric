package net.fabricmc.fabric.api.networking.v1.client;

import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;

import net.fabricmc.fabric.api.networking.v1.PlayContext;

public interface ClientPlayContext extends PlayContext, ClientContext {
	@Override
	ClientPlayerEntity getPlayer();

	@Override
	ClientPlayNetworkHandler getListener();
}
