package net.wiphire.wUtils.mixin;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.font.TextRenderer;

import net.minecraft.client.util.math.MatrixStack;


import static net.wiphire.wUtils.wUtilsClient.mc;

public class DrawText {

    public int timer;

    private static final MatrixStack matrices = new MatrixStack();

    //im way too lazy to type
    //text rendering doesn't work for some reason
    public static void dt() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
                TextRenderer renderer = mc.getInstance().textRenderer;
                renderer.drawWithShadow(matrices, "sssssssssssssssssssssssssssss", 0, 0, 0xffffff);
        });
    }
}
