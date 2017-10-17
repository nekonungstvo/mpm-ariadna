package noppes.mpm.client.animation;

import noppes.mpm.ModelData;
import noppes.mpm.client.MCALibrary.animation.AnimationHandler;
import noppes.mpm.client.MCALibrary.animation.Channel;
import noppes.mpm.client.model.extrapart.ctenotail.ChannelCtenoIdle;

import java.util.HashMap;

public class MPMAnimationHandler extends AnimationHandler {
    public static HashMap<String, Channel> animChannels = new HashMap<>();

    static {
        put(new ChannelCtenoIdle(ChannelCtenoIdle.ANIM_IDLE, 1.0F, 3, Channel.Mode.LOOP_SIN));
    }

    public MPMAnimationHandler(ModelData model) {
        super(model);
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

    private static void put(Channel channel) {
        MPMAnimationHandler.animChannels.put(channel.name, channel);
    }
}