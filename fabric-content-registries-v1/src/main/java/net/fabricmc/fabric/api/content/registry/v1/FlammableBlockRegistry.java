package net.fabricmc.fabric.api.content.registry.v1;

import net.minecraft.block.Block;

public interface FlammableBlockRegistry {
	void register(int flammability, Block block, int disappearancePercent);

	default void register(int flammability, Block block){
		this.register(flammability,block, 20);
	}

	default void register(Block block, int disappearancePercent){
		this.register(5, block, disappearancePercent);
	}

	default void register(Block block){
		this.register(block,5);
	}
}
