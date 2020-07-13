package net.fabricmc.fabric.impl.content.registries;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.block.Block;
import net.minecraft.util.Pair;

import net.fabricmc.fabric.api.content.registry.v1.FlammableBlockRegistry;

public class FlammableBlockRegistryImpl implements FlammableBlockRegistry {

	public static final Map<String, Pair<Integer, Integer>> FLAMMABLE_BLOCKS = Maps.newHashMap();

	@Override
	public void register(int flammability, Block block, int disappearancePercent) {

	}
}
