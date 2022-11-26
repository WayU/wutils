package net.wiphire.wUtils.modules;


import net.minecraft.entity.Entity;
import net.minecraft.util.hit.EntityHitResult;


import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;


import static net.wiphire.wUtils.wUtilsClient.mc;

public class MiddleClickFriend {

    static List<String> friends = new ArrayList<>();

/*
    public static void init(){
        addFriend();
    }

 */
    // lol isnt actually middle click :D
    public static void addFriend () throws FileNotFoundException {
        if(mc.crosshairTarget == null
                || !(mc.crosshairTarget instanceof EntityHitResult))
            return;
        Entity target = ((EntityHitResult)mc.crosshairTarget).getEntity();
        if(friends.contains(target.getEntityName())) {friends.remove(target.getEntityName()); return;}
        friends.add(target.getEntityName());
        //LOGGER.info("Friend added:" + target.getEntityName());
    }


}
