package net.wiphire.wUtils.modules;

import java.util.stream.Stream;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.wiphire.wUtils.event.UpdateListener;
import net.wiphire.wUtils.wUtils;

import static net.wiphire.wUtils.wUtilsClient.mc;

public class TriggerBot {

    public static boolean isOn;
    public static boolean hitMobs;
    static int timer = 1;

    public static void toggleKey() {
        if(isOn) isOn = false;
        else isOn = true;
    }

    public static void toggleKeyMH() {
        if(hitMobs) hitMobs = false;
        else hitMobs = true;
    }




    public static void onEnable() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!isOn) return;
            onUpdates();
        });

    }

    public static void register()
    {
        onEnable();
    }


    public static void onUpdates ()
    {

        if (timer >= 1) timer = timer - 1;
        if(timer != 0) return;
        // don't attack when a container/inventory screen is open
        if(mc.currentScreen instanceof HandledScreen)
            return;

        ClientPlayerEntity player = mc.player;

        if(mc.crosshairTarget == null
                || !(mc.crosshairTarget instanceof EntityHitResult))
            return;

        Entity target = ((EntityHitResult)mc.crosshairTarget).getEntity();
        if (!hitMobs) {
            if (target.getEntityName() == "K4lastaja" || target.getEntityName() == "Wiphire" || target != mc.player)
                return;
        }


        mc.interactionManager.attackEntity(player, target);
        player.swingHand(Hand.MAIN_HAND);
        timer = 15;
    }

}
