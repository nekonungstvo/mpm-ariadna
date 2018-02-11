package noppes.mpm.client.animation;

import noppes.mpm.ModelData;
import noppes.mpm.client.MCALibrary.animation.AnimationHandler;
import noppes.mpm.client.MCALibrary.animation.Channel;
import noppes.mpm.client.model.extrapart.ctenotail.ChannelCtenoIdle;
import noppes.mpm.client.model.extrapart.ctenotail.ChannelCtenoLowered;
import noppes.mpm.client.model.extrapart.ctenotail.ChannelCtenoSit;
import noppes.mpm.client.model.extrapart.flufftail.ChannelFluffyLie;
import noppes.mpm.client.model.extrapart.flufftail.ChannelFluffySit;

import java.util.HashMap;

public class MPMAnimationHandler extends AnimationHandler {
    public static HashMap<String, Channel> animChannels = new HashMap<>();

    static {
        put(new ChannelCtenoIdle());
        put(new ChannelCtenoLowered(1, 2, Channel.Mode.LOOP));
        put(new ChannelCtenoSit(1, 2, Channel.Mode.LOOP));
        put(new ChannelFluffySit());
        put(new ChannelFluffyLie());
    }

    public MPMAnimationHandler(ModelData model) {
        super(model);
    }

    private static void put(Channel channel) {
        MPMAnimationHandler.animChannels.put(channel.name, channel);
    }

    @Override
    public void activateAnimation(String name, float startingFrame) {
        super.activateAnimation(animChannels, name, startingFrame);
    }

    @Override
    public void stopAnimation(String name) {
        super.stopAnimation(animChannels, name);
    }
}