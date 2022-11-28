package net.wiphire.wUtils.mixin;

import net.minecraft.util.Hand;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(FishingBobberEntity.class)
public abstract class AutoFishMixin {
    @Shadow
    private boolean caughtFish;

    boolean on = true;

    @Inject(at = {@At("TAIL")}, method = {"onTrackedDataSet"})
    public void onTrackedDataSet(TrackedData<?> data, CallbackInfo ci) throws InterruptedException {

        MinecraftClient client = MinecraftClient.getInstance();
        if (caughtFish) {
            if (on){
                client.interactionManager.interactItem(client.player, Hand.MAIN_HAND);
                Thread.sleep((int)Math.random()*(1000-700)+700);
                client.interactionManager.interactItem(client.player, Hand.MAIN_HAND);
            }

        }
    }
}