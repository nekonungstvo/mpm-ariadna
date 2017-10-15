package noppes.mpm.client.model.extrapart.ctenotail;

import net.minecraft.entity.Entity;
import noppes.mpm.client.model.MCALibrary.animation.AnimationHandler;
import noppes.mpm.client.model.MCALibrary.animation.Channel;

import java.util.HashMap;

public class AnimationHandlerCtenoTail extends AnimationHandler {
    public static final String ANIM_IDLE = "idle";

    public static HashMap<String, Channel> animChannels = new HashMap<>();

    static {
        animChannels.put(ANIM_IDLE, new ChannelTailIdle(ANIM_IDLE, 0.3F, 3, Channel.LOOP));
    }

    public AnimationHandlerCtenoTail(Entity entity) {
        super(entity);
    }

    @Override
    public void activateAnimation(String name, float startingFrame) {
        super.activateAnimation(animChannels, name, startingFrame);
    }

    @Override
    public void stopAnimation(String name) {
        super.stopAnimation(animChannels, name);
    }

    @Override
    public void fireAnimationEventClientSide(Channel anim, float prevFrame, float frame) {
    }

    @Override
    public void fireAnimationEventServerSide(Channel anim, float prevFrame, float frame) {
    }
}