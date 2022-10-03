package net.wiphire.wUtils.modules;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.wiphire.wUtils.utils.Vec3;

import java.util.ArrayList;
import java.util.List;

import static net.wiphire.wUtils.wUtilsClient.mc;

public class AimAssist {

    private static final List<Entity> targetList = new ArrayList<>();

    static boolean shouldAim = false;

    public static void toggleState() {
        if (shouldAim) shouldAim = false;
        else shouldAim = true;
        targetList.clear();
    }



    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {


            if (shouldAim) {
                for (Entity entity : mc.world.getEntities()) {
                    if (entity == null || !entity.isInRange(mc.player, 4.3)) return;
                    targetList.add(entity);
                }
                targetList.sort((e1, e2) -> sortHealth(e1, e2));
                aim(targetList.get(0), 20.0);
            }
            else return;

        });
    }

    private static int sortHealth(Entity e1, Entity e2) {
        boolean e1l = e1 instanceof LivingEntity;
        boolean e2l = e2 instanceof LivingEntity;

        if (!e1l && !e2l) return 0;
        else if (e1l && !e2l) return 1;
        else if (!e1l) return -1;

        return Float.compare(((LivingEntity) e1).getHealth(), ((LivingEntity) e2).getHealth());
    }


    private static void aim(Entity target, double delta) {
        if ((!target.isInRange(mc.player, 4.3))) {targetList.remove(targetList.get(0)); return; }
        Vec3 vec3d1 = new Vec3();

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
            toRotate = 20 * (deltaAngle >= 0 ? 1 : -1) * delta;
            if ((toRotate >= 0 && toRotate > deltaAngle) || (toRotate < 0 && toRotate < deltaAngle)) toRotate = deltaAngle;
            mc.player.setYaw(mc.player.getYaw() + (float) toRotate);


        // Pitch
        double idk = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
        angle = -Math.toDegrees(Math.atan2(deltaY, idk));


            deltaAngle = MathHelper.wrapDegrees(angle - mc.player.getPitch());
            toRotate = 20 * (deltaAngle >= 0 ? 1 : -1) * delta;
            if ((toRotate >= 0 && toRotate > deltaAngle) || (toRotate < 0 && toRotate < deltaAngle)) toRotate = deltaAngle;
            mc.player.setPitch(mc.player.getPitch() + (float) toRotate);

    }
}
