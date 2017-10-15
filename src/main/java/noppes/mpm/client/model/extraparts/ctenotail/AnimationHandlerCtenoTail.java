package noppes.mpm.client.model.extraparts.ctenotail;

import noppes.mpm.client.model.MCALibrary.IMCAnimatedEntity;
import noppes.mpm.client.model.MCALibrary.animation.AnimationHandler;
import noppes.mpm.client.model.MCALibrary.animation.Channel;

import java.util.HashMap;

public class AnimationHandlerCtenoTail extends AnimationHandler {
    /**
     * Map with all the animations.
     */
    public static HashMap<String, Channel> animChannels = new HashMap<String, Channel>();

    static {
        animChannels.put("tailIdle", new ChannelTailIdle("tailIdle", 0.3F, 3, Channel.LOOP));
    }

    public AnimationHandlerCtenoTail(IMCAnimatedEntity entity) {
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