package net.fabricmc.fabric.mixin.client.keybinding;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.impl.client.keybinding.KeyBindingRegistryImpl;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(GameOptions.class)
public class MixinGameOptions {
	@Shadow
	public KeyBinding[] keysAll;

	@Inject(at = @At("HEAD"), method = "method_2336")
	public void loadHook(CallbackInfo info) {
		keysAll = KeyBindingRegistryImpl.process(keysAll);
	}
}
