package net.fabricmc.fabric.mixin.event.lifecycle.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ClientChunkCache;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientChunkEvents;

@Environment(EnvType.CLIENT)
@Mixin(ClientChunkCache.class)
public abstract class MixinClientChunkCache {
	@Shadow
	private World field_3987;

	@Shadow
	public abstract Chunk getChunk(int i, int j);

	@Inject(at = @At("RETURN"), method = "method_3120")
	public void chunkUnload(int i, int j, CallbackInfo ci) {
		ClientChunkEvents.CHUNK_UNLOAD.invoker().onChunkUnload((ClientWorld) this.field_3987, this.getChunk(i, j));
	}

	@Inject(at = @At("RETURN"), method = "method_3121", locals = LocalCapture.CAPTURE_FAILEXCEPTION)
	public void chunkLoad(int i, int j, CallbackInfoReturnable<Chunk> cir, Chunk chunk) {
		ClientChunkEvents.CHUNK_LOAD.invoker().onChunkLoad((ClientWorld) this.field_3987, chunk);
	}
}
