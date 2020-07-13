package net.fabricmc.fabric.mixin.content.registries;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;

import net.fabricmc.fabric.impl.content.registries.FlammableBlockRegistryImpl;

@Mixin(FireBlock.class)
public class MixinFireBlock {
	@Inject(at = @At("RETURN"), method = "registerDefaultFlammables")
	private static void registerFlammables(CallbackInfo info){
		FlammableBlockRegistryImpl.FLAMMABLE_BLOCKS.forEach(Blocks.FIRE::registerFlammableBlock);
	}
}
