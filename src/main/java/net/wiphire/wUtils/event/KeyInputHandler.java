package net.wiphire.wUtils.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.option.KeyBinding;
import net.wiphire.wUtils.modules.aAssist;

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
}
