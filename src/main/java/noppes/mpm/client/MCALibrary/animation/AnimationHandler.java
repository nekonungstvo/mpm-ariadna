package noppes.mpm.client.MCALibrary.animation;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;
import noppes.mpm.ModelData;
import noppes.mpm.client.MCALibrary.MCAModelRenderer;
import noppes.mpm.client.MCALibrary.math.FastMath;
import noppes.mpm.client.MCALibrary.math.Quaternion;
import noppes.mpm.client.MCALibrary.math.Vector3f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class AnimationHandler {
    public static final AnimTickHandler animTickHandler = new AnimTickHandler();
    /**
     * List of all the activate legsAnimationHandler of this Entity.
     */
    public ArrayList<Channel> animCurrentChannels = new ArrayList<>();
    /**
     * Previous time of every active animation.
     */
    public HashMap<String, Long> animPrevTime = new HashMap<String, Long>();
    /**
     * Current frame of every active animation.
     */
    public HashMap<String, Float> animCurrentFrame = new HashMap<String, Float>();
    /**
     * Owner of this handler.
     */
    private ModelData animatedModel;
    /**
     * Contains the unique names of the events that have been already fired during each animation.
     * It becomes empty at the end of every animation. The key is the animation name and the value is the list of already-called events.
     */
    private HashMap<String, ArrayList<String>> animationEvents = new HashMap<String, ArrayList<String>>();

    public AnimationHandler(ModelData model) {
        animTickHandler.addModel(model);
        animatedModel = model;
    }

    /**
     * Update animation values. Return false if the animation should stop.
     */
    public static boolean updateAnimation(Channel channel, HashMap<String, Long> prevTimeAnim, HashMap<String, Float> prevFrameAnim) {
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side.isServer() || (side.isClient() && !isGamePaused())) {
            if (channel.mode != Channel.Mode.CUSTOM) {
                long prevTime = prevTimeAnim.get(channel.name);
                float prevFrame = prevFrameAnim.get(channel.name);

                long currentTime = System.nanoTime();
                double deltaTime = (currentTime - prevTime) / 1000000000.0;

                float fps = channel.fps;
                if (channel.mode == Channel.Mode.LOOP_SIN) {
                    final float progress = prevFrame / (channel.totalFrames - 1);
                    final float sin = MathHelper.sin(progress * FastMath.FOUR_PI - FastMath.HALF_PI);
                    final float rate = mapSin(sin, 0.05f, 1f);
                    fps *= rate;
                }

                float numberOfSkippedFrames = (float) (deltaTime * fps);
                float currentFrame = prevFrame + numberOfSkippedFrames;

                if (currentFrame < channel.totalFrames - 1) //-1 as the first frame mustn't be "executed" as it is the starting situation
                {
                    prevTimeAnim.put(channel.name, currentTime);
                    prevFrameAnim.put(channel.name, currentFrame);
                    return true;
                } else {
                    if (channel.mode.isLoop()) {
                        prevTimeAnim.put(channel.name, currentTime);
                        prevFrameAnim.put(channel.name, 0F);
                        return true;
                    }
                    return false;
                }
            } else {
                return true;
            }
        } else {
            long currentTime = System.nanoTime();
            prevTimeAnim.put(channel.name, currentTime);
            return true;
        }
    }

    @SideOnly(Side.CLIENT)
    private static boolean isGamePaused() {
        net.minecraft.client.Minecraft MC = net.minecraft.client.Minecraft.getMinecraft();
        return MC.isSingleplayer() && MC.currentScreen != null && MC.currentScreen.doesGuiPauseGame() && !MC.getIntegratedServer().getPublic();
    }

    /**
     * Apply legsAnimationHandler if running or apply initial values.
     * Must be called only by the model class.
     */
    @SideOnly(Side.CLIENT)
    public static void performAnimationInModel(HashMap<String, MCAModelRenderer> parts, AnimationHandler animationHandler) {
        for (Map.Entry<String, MCAModelRenderer> entry : parts.entrySet()) {
            String boxName = entry.getKey();
            MCAModelRenderer box = entry.getValue();

            boolean anyRotationApplied = false;
            boolean anyTranslationApplied = false;
            boolean anyCustomAnimationRunning = false;

            for (Channel channel : animationHandler.animCurrentChannels) {
                if (channel.mode != Channel.Mode.CUSTOM) {
                    float currentFrame = animationHandler.animCurrentFrame.get(channel.name);

                    //Rotations
                    KeyFrame prevRotationKeyFrame = channel.getPreviousRotationKeyFrameForBox(boxName, animationHandler.animCurrentFrame.get(channel.name));
                    int prevRotationKeyFramePosition = prevRotationKeyFrame != null ? channel.getKeyFramePosition(prevRotationKeyFrame) : 0;

                    KeyFrame nextRotationKeyFrame = channel.getNextRotationKeyFrameForBox(boxName, animationHandler.animCurrentFrame.get(channel.name));
                    int nextRotationKeyFramePosition = nextRotationKeyFrame != null ? channel.getKeyFramePosition(nextRotationKeyFrame) : 0;

                    float SLERPProgress = (currentFrame - (float) prevRotationKeyFramePosition) / ((float) (nextRotationKeyFramePosition - prevRotationKeyFramePosition));
                    if (SLERPProgress > 1F || SLERPProgress < 0F) {
                        SLERPProgress = 1F;
                    }

                    if (prevRotationKeyFramePosition == 0 && prevRotationKeyFrame == null && !(nextRotationKeyFramePosition == 0)) {
                        Quaternion currentQuat = new Quaternion();
                        currentQuat.slerp(parts.get(boxName).getDefaultRotationAsQuaternion(), nextRotationKeyFrame.modelRenderersRotations.get(boxName), SLERPProgress);
                        box.getRotationMatrix().set(currentQuat).transpose();

                        anyRotationApplied = true;
                    } else if (prevRotationKeyFramePosition == 0 && prevRotationKeyFrame != null && !(nextRotationKeyFramePosition == 0)) {
                        Quaternion currentQuat = new Quaternion();
                        currentQuat.slerp(prevRotationKeyFrame.modelRenderersRotations.get(boxName), nextRotationKeyFrame.modelRenderersRotations.get(boxName), SLERPProgress);
                        box.getRotationMatrix().set(currentQuat).transpose();

                        anyRotationApplied = true;
                    } else if (!(prevRotationKeyFramePosition == 0) && !(nextRotationKeyFramePosition == 0)) {
                        Quaternion currentQuat = new Quaternion();
                        currentQuat.slerp(prevRotationKeyFrame.modelRenderersRotations.get(boxName), nextRotationKeyFrame.modelRenderersRotations.get(boxName), SLERPProgress);
                        box.getRotationMatrix().set(currentQuat).transpose();

                        anyRotationApplied = true;
                    }


                    //Translations
                    KeyFrame prevTranslationKeyFrame = channel.getPreviousTranslationKeyFrameForBox(boxName, animationHandler.animCurrentFrame.get(channel.name));
                    int prevTranslationsKeyFramePosition = prevTranslationKeyFrame != null ? channel.getKeyFramePosition(prevTranslationKeyFrame) : 0;

                    KeyFrame nextTranslationKeyFrame = channel.getNextTranslationKeyFrameForBox(boxName, animationHandler.animCurrentFrame.get(channel.name));
                    int nextTranslationsKeyFramePosition = nextTranslationKeyFrame != null ? channel.getKeyFramePosition(nextTranslationKeyFrame) : 0;

                    float LERPProgress = (currentFrame - (float) prevTranslationsKeyFramePosition) / ((float) (nextTranslationsKeyFramePosition - prevTranslationsKeyFramePosition));
                    if (LERPProgress > 1F) {
                        LERPProgress = 1F;
                    }

                    if (prevTranslationsKeyFramePosition == 0 && prevTranslationKeyFrame == null && !(nextTranslationsKeyFramePosition == 0)) {
                        Vector3f startPosition = parts.get(boxName).getPositionAsVector();
                        Vector3f endPosition = nextTranslationKeyFrame.modelRenderersTranslations.get(boxName);
                        Vector3f currentPosition = new Vector3f(startPosition);
                        currentPosition.interpolate(endPosition, LERPProgress);
                        box.setRotationPoint(currentPosition.x, currentPosition.y, currentPosition.z);

                        anyTranslationApplied = true;
                    } else if (prevTranslationsKeyFramePosition == 0 && prevTranslationKeyFrame != null && !(nextTranslationsKeyFramePosition == 0)) {
                        Vector3f startPosition = prevTranslationKeyFrame.modelRenderersTranslations.get(boxName);
                        Vector3f endPosition = nextTranslationKeyFrame.modelRenderersTranslations.get(boxName);
                        Vector3f currentPosition = new Vector3f(startPosition);
                        currentPosition.interpolate(endPosition, LERPProgress);
                        box.setRotationPoint(currentPosition.x, currentPosition.y, currentPosition.z);
                    } else if (!(prevTranslationsKeyFramePosition == 0) && !(nextTranslationsKeyFramePosition == 0)) {
                        Vector3f startPosition = prevTranslationKeyFrame.modelRenderersTranslations.get(boxName);
                        Vector3f endPosition = nextTranslationKeyFrame.modelRenderersTranslations.get(boxName);
                        Vector3f currentPosition = new Vector3f(startPosition);
                        currentPosition.interpolate(endPosition, LERPProgress);
                        box.setRotationPoint(currentPosition.x, currentPosition.y, currentPosition.z);

                        anyTranslationApplied = true;
                    }
                } else {
                    anyCustomAnimationRunning = true;

                    ((CustomChannel) channel).update(parts, animationHandler);
                }
            }

            //Set the initial values for each box if necessary
            if (!anyRotationApplied && !anyCustomAnimationRunning) {
                box.resetRotationMatrix();
            }
            if (!anyTranslationApplied && !anyCustomAnimationRunning) {
                box.resetRotationPoint();
            }
        }
    }

    private static float mapSin(float v, float min, float max) {
        final float nv = (v + 1f) / 2f;
        return min + nv * (max - min);
    }

    private static float map(float v, float min, float max, float min2, float max2) {
        final float nv = (v - min) / (max - min);
        return min2 + nv * (max2 - min2);
    }

    public void activateAnimation(HashMap<String, Channel> animChannels, String name, float startingFrame) {
        if (animChannels.get(name) != null) {
            Channel selectedChannel = animChannels.get(name);
            int indexToRemove = animCurrentChannels.indexOf(selectedChannel);
            if (indexToRemove != -1) {
                animCurrentChannels.remove(indexToRemove);
            }

            animCurrentChannels.add(selectedChannel);
            animPrevTime.put(name, System.nanoTime());
            animCurrentFrame.put(name, startingFrame);
            animationEvents.computeIfAbsent(name, k -> new ArrayList<>());
        } else {
            System.out.println("The animation called " + name + " doesn't exist!");
        }
    }

    public void keepAnimation(String name, float startingFrame) {
        if (!isAnimationActive(name))
            activateAnimation(name, startingFrame);
    }

    public abstract void activateAnimation(String name, float startingFrame);

    public void stopAnimation(HashMap<String, Channel> animChannels, String name) {
        Channel selectedChannel = animChannels.get(name);
        if (selectedChannel != null) {
            int indexToRemove = animCurrentChannels.indexOf(selectedChannel);
            if (indexToRemove != -1) {
                animCurrentChannels.remove(indexToRemove);
                animPrevTime.remove(name);
                animCurrentFrame.remove(name);
                animationEvents.get(name).clear();
            }
        } else {
            System.out.println("The animation called " + name + " doesn't exist!");
        }
    }

    public abstract void stopAnimation(String name);

    public void animationsUpdate() {
        for (Iterator<Channel> it = animCurrentChannels.iterator(); it.hasNext(); ) {
            Channel anim = it.next();
            boolean animStatus = updateAnimation(anim, animPrevTime, animCurrentFrame);
            if (!animStatus) {
                it.remove();
                animPrevTime.remove(anim.name);
                animCurrentFrame.remove(anim.name);
                animationEvents.get(anim.name).clear();
            }
        }
    }

    public boolean isAnimationActive(String name) {
        boolean animAlreadyUsed = false;
        for (Channel anim : animatedModel.animationHandler.animCurrentChannels) {
            if (anim.name.equals(name)) {
                animAlreadyUsed = true;
                break;
            }
        }

        return animAlreadyUsed;
    }

    /**
     * Check if the animation event has already been called.
     */
    public boolean alreadyCalledEvent(String animName, String eventName) {
        if (animationEvents.get(animName) == null) {
            System.out.println("Cannot check for event " + eventName + "! Animation " + animName + "does not exist or is not active.");
            return true;
        }
        return animationEvents.get(animName).contains(eventName);
    }

    /**
     * Set the animation event as "called", so it won't be fired again.
     */
    public void setCalledEvent(String animName, String eventName) {
        if (animationEvents.get(animName) != null) {
            animationEvents.get(animName).add(eventName);
        } else {
            System.out.println("Cannot set event " + eventName + "! Animation " + animName + "does not exist or is not active.");
        }
    }
}
