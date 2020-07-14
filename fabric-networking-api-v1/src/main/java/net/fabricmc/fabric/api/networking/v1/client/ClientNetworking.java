/*
 * Copyright (c) 2016, 2017, 2018, 2019 FabricMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.fabricmc.fabric.api.networking.v1.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.api.networking.v1.PacketChannelCallback;
import net.fabricmc.fabric.api.networking.v1.PacketListenerCallback;
import net.fabricmc.fabric.api.networking.v1.PacketReceiver;
import net.fabricmc.fabric.api.networking.v1.PlayPacketSender;
import net.fabricmc.fabric.impl.networking.client.ClientNetworkingDetails;

@Environment(EnvType.CLIENT)
public final class ClientNetworking {
	public static final Event<PacketListenerCallback<ClientPlayNetworkHandler>> PLAY_INITIALIZED = EventFactory
			.createArrayBacked(PacketListenerCallback.class, callbacks -> handler -> {
				for (PacketListenerCallback<ClientPlayNetworkHandler> callback : callbacks) {
					callback.handle(handler);
				}
			});

	public static final Event<PacketListenerCallback<ClientPlayNetworkHandler>> PLAY_DISCONNECTED = EventFactory
			.createArrayBacked(PacketListenerCallback.class, callbacks -> handler -> {
				for (PacketListenerCallback<ClientPlayNetworkHandler> callback : callbacks) {
					callback.handle(handler);
				}
			});

	public static final Event<PacketChannelCallback<ClientPlayNetworkHandler>> CHANNEL_REGISTERED = EventFactory
			.createArrayBacked(PacketChannelCallback.class, callbacks -> (handler, channels) -> {
				for (PacketChannelCallback<ClientPlayNetworkHandler> callback : callbacks) {
					callback.handle(handler, channels);
				}
			});

	public static final Event<PacketChannelCallback<ClientPlayNetworkHandler>> CHANNEL_UNREGISTERED = EventFactory
			.createArrayBacked(PacketChannelCallback.class, callbacks -> (handler, channels) -> {
				for (PacketChannelCallback<ClientPlayNetworkHandler> callback : callbacks) {
					callback.handle(handler, channels);
				}
			});

	public static PlayPacketSender getPlaySender() throws IllegalStateException {
		ClientPlayerEntity player = MinecraftClient.getInstance().player;

		if (player == null) {
			throw new IllegalStateException("Cannot get packet sender when not in game!");
		}

		return getPlaySender(player.networkHandler);
	}

	public static PlayPacketSender getPlaySender(ClientPlayNetworkHandler handler) {
		return ClientNetworkingDetails.getAddon(handler);
	}

	public static PacketReceiver<ClientPlayContext> getPlayReceiver() {
		return ClientNetworkingDetails.PLAY;
	}

	public static PacketReceiver<ClientLoginContext> getLoginReceiver() {
		return ClientNetworkingDetails.LOGIN;
	}
}
