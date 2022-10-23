package net.wiphire.wUtils.modules;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.wiphire.wUtils.utils.Vec3;
import net.wiphire.wUtils.wUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.util.ArrayList;
import java.util.List;

import static net.wiphire.wUtils.wUtilsClient.mc;

public class AimAssist {

    private static final List<Entity> targetList = new ArrayList<>();

    private static Entity target;

    static boolean shouldAim = false;
    static boolean shouldAimInvis = false;
    static boolean hitMobs = false;



    public static void toggleState() {
        if (shouldAim) shouldAim = false;
        else {shouldAim = true;}
        targetList.clear();
    }

    public static void toggleMob() {
        if (hitMobs) hitMobs = false;
        else hitMobs = true;
    }
    public static void toggleInvis() {
        wUtils.LOGGER.info("e");
        if (shouldAimInvis) shouldAimInvis = false;
        else shouldAimInvis = true;
    }


    public static Entity get() {
        if (mc.player == null) return null;
        targetList.clear();
        getList(targetList, 1);
        if (!targetList.isEmpty()) {
            return targetList.get(0);
        }

        return null;
    }

    public static void getList(List<Entity> targetList, int maxCount) {
        if (mc.player == null) return;
        targetList.clear();

        for (Entity entity : mc.world.getEntities()) {
            if (entity != null && entity != mc.player && mc.player.distanceTo(entity ) <= 3.8 && entity.getEntityName() != "K4lastaja" && entity.getEntityName() != "Wiphire" && entity.isAlive() && entity.isAttackable() && !(mc.player.isTeammate(entity))) {
                if (!shouldAimInvis && entity.isInvisible()) return;
                if (!hitMobs && entity instanceof PlayerEntity) targetList.add(entity);
                    else if (hitMobs) targetList.add(entity);
            }

        }


        targetList.sort((e1, e2) -> sortHealth(e1, e2));
        targetList.removeIf(entity -> targetList.indexOf(entity) > maxCount -1);
    }


    public static void register() {
        AimAssist aim = new AimAssist();

        // I realise,that this could be made more efficient, and less resource taking, but my pc is strong enough and im lazy
        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (shouldAim) {
                    target = get();
                    aim.aim(target, 0.74);
                }
            }
        });
        timer.setRepeats(true); // not sure if needed
        timer.start();
        /*
        ClientTickEvents.END_CLIENT_TICK.register(client ->  {

            if (shouldAim) {
                target = get();
                aim.aim(target, 0.74);
            }
            else return;

        });

         */
    }



    private static int sortHealth(Entity e1, Entity e2) {
        boolean e1l = e1 instanceof LivingEntity;
        boolean e2l = e2 instanceof LivingEntity;

        if (!e1l && !e2l) return 0;
        else if (e1l && !e2l) return 1;
        else if (!e1l) return -1;

        return Float.compare(((LivingEntity) e1).getHealth(), ((LivingEntity) e2).getHealth());
    }



    Vec3 vec3d1 = new Vec3();

    private void aim(Entity target, double delta) {

        if (target == null) return;
        vec3d1.set(target, delta);

        vec3d1.add(0, target.getEyeHeight(target.getPose()) / 1.3, 0);


        double deltaX = vec3d1.x - mc.player.getX();
        double deltaZ = vec3d1.z - mc.player.getZ();
        double deltaY = vec3d1.y - (mc.player.getY() + mc.player.getEyeHeight(mc.player.getPose()));

        // Yaw
        double angle = Math.toDegrees(Math.atan2(deltaZ, deltaX)) - 90;
        double deltaAngle;
        double toRotate;


            deltaAngle = MathHelper.wrapDegrees(angle - mc.player.getYaw());
            toRotate = 4 * (deltaAngle >= 0 ? 1 : -1) * delta;
            if ((toRotate >= 0 && toRotate > deltaAngle) || (toRotate < 0 && toRotate < deltaAngle)) toRotate = deltaAngle;
            mc.player.setYaw(mc.player.getYaw() + (float) toRotate);


        // Pitch
        double idk = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
        angle = -Math.toDegrees(Math.atan2(deltaY, idk));


            deltaAngle = MathHelper.wrapDegrees(angle - mc.player.getPitch());
            toRotate = 4 * (deltaAngle >= 0 ? 1 : -1) * delta;
            if ((toRotate >= 0 && toRotate > deltaAngle) || (toRotate < 0 && toRotate < deltaAngle)) toRotate = deltaAngle;
            mc.player.setPitch(mc.player.getPitch() + (float) toRotate);


    }
}
