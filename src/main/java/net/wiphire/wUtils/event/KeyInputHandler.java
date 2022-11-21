package net.wiphire.wUtils.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;


import net.wiphire.wUtils.modules.AimAssist;
import net.wiphire.wUtils.modules.MiddleClickFriend;
import net.wiphire.wUtils.modules.TriggerBot;

import org.lwjgl.glfw.GLFW;

import java.io.FileNotFoundException;
import java.lang.String;
import static net.wiphire.wUtils.wUtilsClient.mc;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_WUTILS = "key.category.wutils";
    public static final String KEY_CALL_FUNC = "key.wutils.aimassist";
    public static final String KEY_CALL_FUNC2 = "key.wutils.triggerbot";
    public static final String KEY_CALL_FUNC3 = "key.wutils.mobtoggle";
    public static final String KEY_CALL_FUNC4 = "key.wutils.invistoggle";
    public static final String KEY_CALL_FUNC5 = "key.wutils.addfriend";

    public static KeyBinding funcKey;
    public static KeyBinding funcKey2;
    public static KeyBinding funcKey3;
    public static KeyBinding funcKey4;
    public static KeyBinding funcKey5;

    public static void registerKeyInputs() {

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if(funcKey.wasPressed()) {
            //    wUtils.LOGGER.info("key1 worked atleast");
                TriggerBot.toggleKey();

            }
            if (funcKey2.wasPressed()) {
            //    wUtils.LOGGER.info("key2 worked atleast");
                AimAssist.toggleState();

            }

            if (funcKey3.wasPressed()) {
                //    wUtils.LOGGER.info("key3 worked atleast");
                AimAssist.toggleMob();
                TriggerBot.toggleKeyMH();
            }
            if (funcKey4.wasPressed()) {
                AimAssist.toggleInvis();
            }
            if (funcKey5.wasPressed()) {

                try {
                    MiddleClickFriend.addFriend();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static void register() {
        funcKey= KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_CALL_FUNC,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_O,
                KEY_CATEGORY_WUTILS
        ));

        funcKey2= KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_CALL_FUNC2,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                KEY_CATEGORY_WUTILS
        ));

        funcKey3= KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_CALL_FUNC3,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                KEY_CATEGORY_WUTILS
        ));
        funcKey4= KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_CALL_FUNC4,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_H,
                KEY_CATEGORY_WUTILS
        ));

        funcKey5= KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_CALL_FUNC5,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_B ,
                KEY_CATEGORY_WUTILS
        ));
        registerKeyInputs();
    }

}