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

package net.fabricmc.fabric.mixin.command;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.command.AbstractCommand;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;

import net.fabricmc.fabric.impl.command.CommandSide;
import net.fabricmc.fabric.impl.command.FabricCommandRegistryImpl;

@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer {
//	@Inject(method = "<init>", at = @At("TAIL"))
//	public void registerCommands(CallbackInfo info) {
//		FabricCommandRegistryImpl.getCommandMap().forEach((command, side) -> {
//			boolean dedicated = MinecraftServer.getServer().isDedicated();
//
//			if (!(dedicated) && side.isIntegrated()) {
//				this.registerCommand(command);
//			}
//
//			if (dedicated && side.isDedicated()) {
//				this.registerCommand(command);
//			}
//
//			FabricCommandRegisteredCallback.EVENT.invoker().onCommandRegistered(MinecraftServer.getServer(), command, side);
//		});
//
//		AbstractCommand.setCommandProvider(this);
//	}

	@Shadow
	public abstract boolean isDedicated();

	@Inject(at = @At("TAIL"), method = "createCommandManager")
	public void interceptCommands(CallbackInfoReturnable<CommandManager> cir) {
		FabricCommandRegistryImpl.getCommandMap().forEach((AbstractCommand command, CommandSide side) -> {
			if (side.isDedicated() && this.isDedicated()) {
				cir.getReturnValue().registerCommand(command);
			}
			if (side.isIntegrated() && !this.isDedicated()) {
				cir.getReturnValue().registerCommand(command);
			}
		});
	}
}
