package net.wiphire.wUtils.modules;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.wiphire.wUtils.utils.Vec3;
import net.wiphire.wUtils.wUtils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.GameMode;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;
import java.util.List;

import static net.wiphire.wUtils.wUtilsClient.mc;


    public class aAssist {
    public static boolean isEnabled = false;
    private static final List<Entity> targetList = new ArrayList<>();

    public static void getTLIST() {
        targetList.clear();

        for (Entity entity : mc.world.getEntities()) {
            if (entity != null && entity.getEntityName() != "K4lastaja" && entity.getEntityName() != "Wiphire"/* && entity instanceof PlayerEntity || entity == mc.player*/) targetList.add(entity);
        }
    }

    public static void toggleEnableState(){
        if (isEnabled){
            getTLIST();
            isEnabled = false;
            wUtils.LOGGER.info(targetList.get(0).getEntityName());
        }
        else
        {
            isEnabled = true;
        }
    }

    public void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (isEnabled) {
                aim(targetList.get(0), 5.0 );
            }
        });
    }

    public Vec3 vec3d1;

    private void aim(Entity target, double delta) {
        vec3d1.set(target, delta);

        vec3d1.add(0, target.getEyeHeight(target.getPose()) / 2, 0);

        double deltaX = vec3d1.x - mc.player.getX();
        double deltaZ = vec3d1.z - mc.player.getZ();
        double deltaY = vec3d1.y - (mc.player.getY() + mc.player.getEyeHeight(mc.player.getPose()));

        // Yaw
        double angle = Math.toDegrees(Math.atan2(deltaZ, deltaX)) - 90;
        double deltaAngle;
        double toRotate;


        deltaAngle = MathHelper.wrapDegrees(angle - mc.player.getYaw());
        toRotate = 5 * (deltaAngle >= 0 ? 1 : -1) * delta;
        if ((toRotate >= 0 && toRotate > deltaAngle) || (toRotate < 0 && toRotate < deltaAngle)) toRotate = deltaAngle;
        mc.player.setYaw(mc.player.getYaw() + (float) toRotate);


            // Pitch
        double idk = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
        angle = -Math.toDegrees(Math.atan2(deltaY, idk));


        deltaAngle = MathHelper.wrapDegrees(angle - mc.player.getPitch());
        toRotate = 5 * (deltaAngle >= 0 ? 1 : -1) * delta;
        if ((toRotate >= 0 && toRotate > deltaAngle) || (toRotate < 0 && toRotate < deltaAngle)) toRotate = deltaAngle;
        mc.player.setPitch(mc.player.getPitch() + (float) toRotate);

    }
    }
