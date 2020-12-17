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

package net.fabricmc.fabric.mixin.event.lifecycle;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.MinecraftServer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;setupServer()Z"), method = "run")
	private void beforeSetupServer(CallbackInfo info) {
		ServerLifecycleEvents.SERVER_STARTING.invoker().onServerStarting((MinecraftServer) (Object) this);
	}

	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;setFavicon(Lnet/minecraft/server/ServerMetadata;)V", ordinal = 0), method = "run")
	private void afterSetupServer(CallbackInfo info) {
		ServerLifecycleEvents.SERVER_STARTED.invoker().onServerStarted((MinecraftServer) (Object) this);
	}

	@Inject(at = @At("HEAD"), method = "shutdown")
	private void beforeShutdownServer(CallbackInfo info) {
		ServerLifecycleEvents.SERVER_STOPPING.invoker().onServerStopping((MinecraftServer) (Object) this);
	}

	@Inject(at = @At("TAIL"), method = "shutdown")
	private void afterShutdownServer(CallbackInfo info) {
		ServerLifecycleEvents.SERVER_STOPPED.invoker().onServerStopped((MinecraftServer) (Object) this);
	}

	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;tickBlockEntities()V"), method = "method_33183")
	private void onStartTick(CallbackInfo ci) {
		ServerTickEvents.START_SERVER_TICK.invoker().onStartTick((MinecraftServer) (Object) this);
	}

	@Inject(at = @At("TAIL"), method = "method_33183")
	private void onEndTick(CallbackInfo info) {
		ServerTickEvents.END_SERVER_TICK.invoker().onEndTick((MinecraftServer) (Object) this);
	}
}
