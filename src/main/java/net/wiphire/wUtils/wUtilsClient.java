package net.wiphire.wUtils;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.wiphire.wUtils.event.KeyInputHandler;
import net.minecraft.client.MinecraftClient;
import net.wiphire.wUtils.modules.TriggerBot;

@Environment(EnvType.CLIENT)
public class wUtilsClient implements ClientModInitializer {

    public static MinecraftClient mc;

    @Override
    public void onInitializeClient() {
        mc = MinecraftClient.getInstance();
        TriggerBot.register();
        KeyInputHandler.register();
    }
}
