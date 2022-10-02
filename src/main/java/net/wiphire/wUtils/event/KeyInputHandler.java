package net.wiphire.wUtils.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import net.minecraft.client.util.math.MatrixStack;
import net.wiphire.wUtils.modules.TriggerBot;

import net.wiphire.wUtils.wUtils;
import org.lwjgl.glfw.GLFW;
import static net.wiphire.wUtils.wUtilsClient.mc;
import java.lang.String;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_WUTILS = "key.category.wutils";
    public static final String KEY_CALL_FUNC = "key.wutils.onr";
    public static final String KEY_CALL_FUNC2 = "key.wutils.ono";

    public static KeyBinding funcKey;
    public static KeyBinding funcKey2;

    public static void registerKeyInputs() {

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(funcKey.wasPressed()) {
            //    wUtils.LOGGER.info("key1 worked atleast");
                TriggerBot.toggleKey();

            }
            if (funcKey2.wasPressed()) {
            //    wUtils.LOGGER.info("key2 worked atleast");
                TriggerBot.toggleKeyMH();

            }
        });
    }

    public static void register() {
        funcKey= KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_CALL_FUNC,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                KEY_CATEGORY_WUTILS
        ));

        funcKey2= KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_CALL_FUNC2,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_O,
                KEY_CATEGORY_WUTILS
        ));
        registerKeyInputs();
    }

}