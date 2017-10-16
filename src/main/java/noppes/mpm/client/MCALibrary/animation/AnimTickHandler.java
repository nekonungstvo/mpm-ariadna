package noppes.mpm.client.MCALibrary.animation;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import noppes.mpm.ModelData;

import java.util.ArrayList;

public class AnimTickHandler {
    private ArrayList<ModelData> activeModels = new ArrayList<>();
    private ArrayList<ModelData> removableModels = new ArrayList<>();

    public AnimTickHandler() {
        FMLCommonHandler.instance().bus().register(this);
    }

    public void addModel(ModelData entity) {
        activeModels.add(entity);
    }

    //Called when the client ticks.
    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (!activeModels.isEmpty()) {
            if (event.phase == Phase.START) {
                for (ModelData model : activeModels) {
                    model.animationHandler.animationsUpdate();

//                    Entity entity = model.getEntity(Minecraft.getMinecraft().theWorld);
//                    if (entity.isDead) {
//                        removableModels.add(model);
//                    }
                }

                for (ModelData model : removableModels) {
                    activeModels.remove(model);
                }
                removableModels.clear();
            }
        }
    }

//    //Called when the server ticks. Usually 20 ticks a second.
//    @SubscribeEvent
//    public void onServerTick(TickEvent.ServerTickEvent event) {
//        if (!activeModels.isEmpty()) {
//            if (event.phase == Phase.START) {
//                for (Entity entity : activeModels) {
//                    entity.getAnimationHandler().animationsUpdate();
//
//                    if (((Entity) entity).isDead) {
//                        removableModels.add(entity);
//                    }
//                }
//
//                for (Entity entity : removableModels) {
//                    activeModels.remove(entity);
//                }
//                removableModels.clear();
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
