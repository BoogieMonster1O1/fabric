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

package net.fabricmc.fabric.impl.client.keybinding;

import java.util.Set;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;

public final class KeyBindingRegistry {
	private static final Logger LOGGER = LogManager.getLogger();

	private static final Set<KeyBinding> moddedKeyBindings = Sets.newHashSet();

	private KeyBindingRegistry() {
	}

	private static Set<String> getCategorySet() {
		return KeyBinding.getCategories();
	}

	private static boolean hasCategory(String categoryTranslationKey) {
		return getCategorySet().contains(categoryTranslationKey);
	}

	public static boolean addCategoryIfAbsent(String categoryTranslationKey) {
		Set<String> set = getCategorySet();

		if (set.contains(categoryTranslationKey)) {
			return false;
		}

		set.add(categoryTranslationKey);
		return true;
	}

	public static KeyBinding registerKeyBinding(KeyBinding binding) {
		for (KeyBinding existingKeyBindings : moddedKeyBindings) {
			if (existingKeyBindings == binding) {
				throw new IllegalArgumentException("Can not register the same keybinding twice!");
			} else if (existingKeyBindings.getTranslationKey().equals(binding.getTranslationKey())) {
				throw new RuntimeException("Attempted to register two key bindings with equal ID: " + binding.getTranslationKey() + "!");
			}
		}

		if (!hasCategory(binding.getCategory())) {
			addCategoryIfAbsent(binding.getCategory());
		}

		MinecraftClient.getInstance().options.keysAll = ArrayUtils.add(MinecraftClient.getInstance().options.keysAll,binding);
		return moddedKeyBindings.add(binding) ? binding : null;
	}
}
