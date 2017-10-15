package noppes.mpm.client.model.MCALibrary.animation;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import net.minecraft.entity.Entity;

import java.util.ArrayList;

public class AnimTickHandler {
    private ArrayList<Entity> activeEntities = new ArrayList<Entity>();
    private ArrayList<Entity> removableEntities = new ArrayList<Entity>();

    public AnimTickHandler() {
        FMLCommonHandler.instance().bus().register(this);
    }

    public void addEntity(Entity entity) {
        activeEntities.add(entity);
    }

//    //Called when the client ticks.
//    @SubscribeEvent
//    public void onClientTick(TickEvent.ClientTickEvent event) {
//        if (!activeEntities.isEmpty()) {
//            if (event.phase == Phase.START) {
//                for (Entity entity : activeEntities) {
//                    entity.getAnimationHandler().animationsUpdate();
//
//                    if (((Entity) entity).isDead) {
//                        removableEntities.add(entity);
//                    }
//                }
//
//                for (Entity entity : removableEntities) {
//                    activeEntities.remove(entity);
//                }
//                removableEntities.clear();
//            }
//        }
//    }
//
//    //Called when the server ticks. Usually 20 ticks a second.
//    @SubscribeEvent
//    public void onServerTick(TickEvent.ServerTickEvent event) {
//        if (!activeEntities.isEmpty()) {
//            if (event.phase == Phase.START) {
//                for (Entity entity : activeEntities) {
//                    entity.getAnimationHandler().animationsUpdate();
//
//                    if (((Entity) entity).isDead) {
//                        removableEntities.add(entity);
//                    }
//                }
//
//                for (Entity entity : removableEntities) {
//                    activeEntities.remove(entity);
//                }
//                removableEntities.clear();
//            }
//        }
//    }

    //Called when a new frame is displayed (See fps)
    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
    }

    //Called when the world ticks
    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent event) {
    }
}
