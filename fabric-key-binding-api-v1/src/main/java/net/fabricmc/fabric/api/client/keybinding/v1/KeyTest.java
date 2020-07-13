package net.fabricmc.fabric.api.client.keybinding.v1;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.KeyBinding;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;

@Environment(EnvType.CLIENT)
public class KeyTest implements ModInitializer {

	public static KeyBinding FOO;
	@Override
	public void onInitialize() {
		FOO = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.null.grave", Keyboard.KEY_GRAVE, "key.categories.gameplay"));
		ClientTickCallback.EVENT.register(this::run);
	}

	public void run(MinecraftClient client) {
		if(FOO.isPressed()){
			System.err.println("pressed");
		}
	}
}
