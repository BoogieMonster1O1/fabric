package net.fabricmc.fabric.impl.client.keybinding;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import net.minecraft.client.options.KeyBinding;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Set;

public final class KeyBindingRegistryImpl {
	private static final Logger LOGGER = LogManager.getLogger();

	private static final Set<KeyBinding> moddedKeyBindings = Sets.newHashSet();

	private KeyBindingRegistryImpl() {
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

		return moddedKeyBindings.add(binding) ? binding : null;
	}

	public static KeyBinding[] process(KeyBinding[] keysAll) {
		List<KeyBinding> newKeysAll = Lists.newArrayList(keysAll);
		newKeysAll.removeAll(moddedKeyBindings);
		newKeysAll.addAll(moddedKeyBindings);
		return newKeysAll.toArray(new KeyBinding[0]);
	}
}
