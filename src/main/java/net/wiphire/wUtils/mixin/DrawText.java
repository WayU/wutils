package net.wiphire.wUtils.mixin;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.font.TextRenderer;
import net.wiphire.wUtils.modules.TriggerBot;
import net.wiphire.wUtils.wUtils;
import net.minecraft.client.util.math.MatrixStack;
import net.wiphire.wUtils.wUtils;

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
