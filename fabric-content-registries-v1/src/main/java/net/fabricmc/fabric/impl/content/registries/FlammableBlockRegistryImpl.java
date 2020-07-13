package net.fabricmc.fabric.impl.content.registries;

import net.minecraft.block.Block;

import net.fabricmc.fabric.api.content.registry.v1.FlammableBlockRegistry;
import net.fabricmc.fabric.api.util.TriMap;

public class FlammableBlockRegistryImpl implements FlammableBlockRegistry {

	public static final TriMap<Block, Integer, Integer> FLAMMABLE_BLOCKS = new TriMap<>();

	@Override
	public void register(int flammability, Block block, int disappearancePercent) {
		FLAMMABLE_BLOCKS.add(block, flammability, disappearancePercent);
	}
}
