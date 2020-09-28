package net.fabricmc.fabric.mixin.command;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.command.Command;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandRegistry;

import net.fabricmc.fabric.api.event.server.CommandRegisteredCallback;

@Mixin(CommandRegistry.class)
public class MixinCommandRegistry{
	@Inject(at = @At("TAIL"), method = "registerCommand")
	public void invoke(Command command, CallbackInfoReturnable<Command> cir) {
		CommandRegisteredCallback.EVENT.invoker().onCommandRegistered(MinecraftServer.getServer(), command);
	}
}
