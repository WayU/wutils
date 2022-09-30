package net.wiphire.wUtils.modules;

import net.wiphire.wUtils.wUtils;
import net.minecraft.client.option.KeyBinding;

public class aAssist {
    public static boolean isEnabled = false;

    public static void lookAt(){

    }

    public static void toggleEnableState(){
        if (isEnabled){
            isEnabled = false;
            wUtils.LOGGER.info("assist set false");
        }
        else
        {
            isEnabled = true;
            wUtils.LOGGER.info("assist set true");
        }
    }
}
