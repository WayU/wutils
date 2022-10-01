package net.wiphire.wUtils.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.wiphire.wUtils.modules.aAssist;
import org.lwjgl.glfw.GLFW;
import static net.wiphire.wUtils.wUtilsClient.mc;
import java.lang.String;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_TUTORIAL = "key.category.tutorial";
    public static final String KEY_CALL_FUNC = "key.wutils.onr";

    public static KeyBinding funcKey;

    public static void registerKeyInputs() {

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(funcKey.wasPressed()) {
                aAssist.toggleEnableState();
            }
        });
    }

    public static void register() {
        funcKey= KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_CALL_FUNC,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                KEY_CATEGORY_TUTORIAL
                ));
        registerKeyInputs();
    }

}
