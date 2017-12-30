package noppes.mpm.client.MCALibrary.animation;

import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import noppes.mpm.ModelData;

import java.util.ArrayList;

public class AnimTickHandler {
    private ArrayList<ModelData> activeModels = new ArrayList<>();
    private ArrayList<ModelData> removableModels = new ArrayList<>();

    public void addModel(ModelData entity) {
        activeModels.add(entity);
    }

    // Called when the client ticks.
    // Called from ClientEventHandler.onRenderTick
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        if (event.phase == Phase.START && !activeModels.isEmpty()) {
            for (ModelData model : activeModels) {
                model.animationHandler.animationsUpdate();
            }
            for (ModelData model : removableModels) {
                activeModels.remove(model);
            }
            removableModels.clear();
        }
    }
}
