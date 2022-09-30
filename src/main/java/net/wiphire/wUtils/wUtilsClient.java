package net.wiphire.wUtils;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.wiphire.wUtils.event.KeyInputHandler;

@Environment(EnvType.CLIENT)
public class wUtilsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        KeyInputHandler.register();
    }
}
