package noppes.mpm.client;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import noppes.mpm.ModelData;
import noppes.mpm.PlayerDataController;
import noppes.mpm.client.model.ModelMPM;
import noppes.mpm.client.model.ModelMpmNewFormat;
import noppes.mpm.client.model.ModelRenderPassHelper;
import noppes.mpm.constants.EnumAnimation;
import org.lwjgl.opengl.GL11;

import java.io.File;
import java.util.Map;
import java.util.UUID;

public class RenderMPM extends RenderPlayer {
    ModelMPM modelBipedMain;
    ModelMPM modelBipedMainNewFormat;
    ModelMPM modelArmorChestplate;
    ModelMPM modelArmor;

    private ModelData data;
    private RendererLivingEntity renderEntity;
    private EntityLivingBase entity;
    private ModelRenderPassHelper renderpass = new ModelRenderPassHelper();

    public RenderMPM() {
        setRenderManager(RenderManager.instance);
        this.modelBipedMain = new ModelMPM(0.0F);
        this.modelBipedMainNewFormat = new ModelMpmNewFormat();
        this.modelArmor = new ModelMPM(0.3F);
        this.modelArmorChestplate = new ModelMPM(0.4F);
    }

    public void setModelData(ModelData data, EntityLivingBase entity) {
        this.data = data;

        if (data.reloadBoxes) {
            this.modelBipedMain.reloadBoxes();
            this.modelBipedMainNewFormat.reloadBoxes();
            data.reloadBoxes = false;
        }

        this.modelBipedMain.setPlayerData(data, entity);
        this.modelBipedMainNewFormat.setPlayerData(data, entity);
        this.modelArmorChestplate.setPlayerData(data, entity);
        this.modelArmor.setPlayerData(data, entity);
    }

    protected void passSpecialRender(EntityLivingBase base, double x, double y, double z) {
        if ((this.data.isSleeping()) || (this.data.animation == EnumAnimation.CRAWLING)) {
            y -= 1.5D;
        } else if (this.data != null)
            y -= this.data.getBodyY();
        base.isSneaking();
        if (this.data.animation == EnumAnimation.SITTING)
            y -= 0.6D;
        super.passSpecialRender(base, x, y, z);
        EntityPlayer player = (EntityPlayer) base;

        Scoreboard scoreboard = ((EntityPlayer) base).getWorldScoreboard();
        ScoreObjective scoreobjective = scoreboard.func_96539_a(2);
        if (scoreobjective != null) {
            y += 0.3D;
        }

        if (func_110813_b(base))
            ChatMessages.getChatMessages(player.getCommandSenderName()).renderMessages(x, y + 0.7D + player.height, z);
    }

    /**
     * @param data pass data for main skin, null otherwise
     */
    private void loadPlayerTexture(ModelData data, File file, ResourceLocation resource, String par1Str) {
        final TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        final ImageBufferDownloadAlt imageBuffer = new ImageBufferDownloadAlt(data);
        final ThreadDownloadImageData object = new ThreadDownloadImageData(file, par1Str, SkinManager.field_152793_a, imageBuffer);
        texturemanager.loadTexture(resource, object);
    }

    public static File getSkinFileForName(String name) {
        final SkinManager skinmanager = Minecraft.getMinecraft().func_152342_ad();

        final String skinSubfolder = name.substring(0, 2);
        final File skinsDir = new File((File) ObfuscationReflectionHelper.getPrivateValue(SkinManager.class, skinmanager, 3), skinSubfolder);

        return new File(skinsDir, name);
    }

    public ResourceLocation loadResource(AbstractClientPlayer player) {
        if (this.data.loaded)
            return this.data.playerResource;

        final Minecraft mc = Minecraft.getMinecraft();
        final SkinManager skinmanager = mc.func_152342_ad();
        final GameProfile gp = player.getGameProfile();
        final Map map = skinmanager.func_152788_a(gp);

        final String url;

        if ((this.data.url != null) && (!this.data.url.isEmpty())) {
            url = this.data.url;
        } else {
            final MinecraftProfileTexture profile_skin = (MinecraftProfileTexture) map.get(MinecraftProfileTexture.Type.SKIN);
            if (profile_skin == null) {
                this.data.loaded = true;
                data.playerResource = player.getLocationSkin();
                return data.playerResource;
            } else {
                url = profile_skin.getUrl();
            }
        }

        final File skinFile = getSkinFileForName(gp.getName());
        if (skinFile.exists())
            skinFile.delete();

        final ResourceLocation location = new ResourceLocation("skins/" + gp.getName());
        loadPlayerTexture(data, skinFile, location, url);
        player.func_152121_a(MinecraftProfileTexture.Type.SKIN, location);

        data.playerResource = location;
        data.loaded = true;
        return location;
    }

    public ResourceLocation loadExtraTexture(AbstractClientPlayer player) {
        if (data.extraUrl == null || data.extraUrl.isEmpty()) return null;
        if (data.extraLoaded) return data.playerExtraTexture;

        final Minecraft mc = Minecraft.getMinecraft();
        final SkinManager skinmanager = mc.func_152342_ad();
        final GameProfile gp = player.getGameProfile();

        final String fileName = "__extra_" + gp.getName();
        final File skinsDir = new File((File) ObfuscationReflectionHelper.getPrivateValue(SkinManager.class, skinmanager, 3), gp.getName().substring(0, 2));
        final File skinFile = new File(skinsDir, fileName);
        if (skinFile.exists())
            skinFile.delete();

        final ResourceLocation location = new ResourceLocation("skins/" + fileName);
        loadPlayerTexture(null, skinFile, location, data.extraUrl);

        data.playerExtraTexture = location;
        data.extraLoaded = true;
        return location;
    }

    public void renderFirstPersonArm(EntityPlayer player) {
        this.data = PlayerDataController.instance.getPlayerData(player);

        if (!this.data.loaded) {
            if (player.ticksExisted > 20) {
                this.data.playerResource = loadResource((AbstractClientPlayer) player);
            } else
                this.data.playerResource = ((AbstractClientPlayer) player).getLocationSkin();
        }
        if (!data.extraUrl.isEmpty() && !data.extraLoaded && data.loaded) {
            this.data.playerExtraTexture = loadExtraTexture((AbstractClientPlayer) player);
        }
        setModelData(this.data, player);

        ModelMPM playerModel = modelBipedMainNewFormat;

        float f = 1.0F;
        GL11.glColor3f(f, f, f);
        playerModel.onGround = 0.0F;
        playerModel.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
        playerModel.renderArms(player, 0.0625F, true);
    }

    public void renderItem(EntityPlayer par1AbstractClientPlayer) {
        ItemStack itemstack1 = par1AbstractClientPlayer.inventory.getCurrentItem();

        if (itemstack1 != null) {
            GL11.glPushMatrix();
            float y = (this.data.arms.scaleY - 1.0F) * 0.7F;

            float x = (1.0F - this.data.body.scaleX) * 0.25F + (1.0F - this.data.arms.scaleX) * 0.075F;
            GL11.glTranslatef(x, this.data.getBodyY(), 0.0F);

            ModelMPM playerModel = modelBipedMainNewFormat;
            playerModel.bipedRightArm.postRender(0.0625F);

            GL11.glTranslatef(-0.0625F, 0.4375F + y, 0.0625F);

            if (par1AbstractClientPlayer.fishEntity != null) {
                itemstack1 = new ItemStack(Items.stick);
            }

            EnumAction enumaction = null;

            if (par1AbstractClientPlayer.getItemInUseCount() > 0) {
                enumaction = itemstack1.getItemUseAction();
            }

            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack1, IItemRenderer.ItemRenderType.EQUIPPED);
            boolean is3D = (customRenderer != null) && (customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack1, IItemRenderer.ItemRendererHelper.BLOCK_3D));

            if ((is3D) || (((itemstack1.getItem() instanceof ItemBlock)) && (RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack1.getItem()).getRenderType())))) {
                float f3 = 0.5F;
                GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
                f3 *= 0.75F;
                GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(-f3, -f3, f3);
            } else if (itemstack1.getItem() == Items.bow) {
                float f3 = 0.625F;
                GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
                GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(-f3, -f3, f3);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            } else if (itemstack1.getItem().isFull3D()) {
                float f3 = 0.625F;

                if (itemstack1.getItem().shouldRotateAroundWhenRendering()) {
                    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glTranslatef(0.0F, -0.125F, 0.0F);
                }

                if ((par1AbstractClientPlayer.getItemInUseCount() > 0) && (enumaction == EnumAction.block)) {
                    GL11.glTranslatef(0.05F, 0.0F, -0.1F);
                    GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
                }

                GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
                GL11.glScalef(-f3, -f3, f3);
                GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            } else {
                float f3 = 0.375F;
                GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
                GL11.glScalef(f3, f3, f3);
                GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
            }


            if (itemstack1.getItem().requiresMultipleRenderPasses()) {
                for (int k = 0; k <= itemstack1.getItem().getRenderPasses(itemstack1.getItemDamage()); k++) {
                    int i = itemstack1.getItem().getColorFromItemStack(itemstack1, k);
                    float f12 = (i >> 16 & 0xFF) / 255.0F;
                    float f4 = (i >> 8 & 0xFF) / 255.0F;
                    float f5 = (i & 0xFF) / 255.0F;
                    GL11.glColor4f(f12, f4, f5, 1.0F);
                    this.renderManager.itemRenderer.renderItem(par1AbstractClientPlayer, itemstack1, k);
                }
            }


            int k = itemstack1.getItem().getColorFromItemStack(itemstack1, 0);
            float f11 = (k >> 16 & 0xFF) / 255.0F;
            float f12 = (k >> 8 & 0xFF) / 255.0F;
            float f4 = (k & 0xFF) / 255.0F;
            GL11.glColor4f(f11, f12, f4, 1.0F);
            this.renderManager.itemRenderer.renderItem(par1AbstractClientPlayer, itemstack1, 0);


            GL11.glPopMatrix();
        }
    }


    protected void rotateCorpse(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
        EntityPlayer player = (EntityPlayer) par1EntityLiving;
        if (!player.isEntityAlive()) {
            super.rotateCorpse(par1EntityLiving, par2, par3, par4);
            return;
        }

        if (player.ridingEntity != null) {
            GL11.glTranslatef(0.0F, this.data.getLegsY(), 0.0F);
        }

        if (this.data.animation == EnumAnimation.SITTING) {
            GL11.glTranslatef(0.0F, -0.6F + this.data.getLegsY(), 0.0F);
        }
        if (this.data.animation == EnumAnimation.SLEEPING_EAST) {
            GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(1.6F + this.data.offsetY(), 0.05F, 0.0F);
            GL11.glRotatef(getDeathMaxRotation(player), 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
        } else if (this.data.animation == EnumAnimation.SLEEPING_NORTH) {
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(1.6F + this.data.offsetY(), 0.05F, 0.0F);
            GL11.glRotatef(getDeathMaxRotation(player), 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
        } else if (this.data.animation == EnumAnimation.SLEEPING_WEST) {
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(1.6F + this.data.offsetY(), 0.05F, 0.0F);
            GL11.glRotatef(getDeathMaxRotation(player), 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
        } else if (this.data.animation == EnumAnimation.SLEEPING_SOUTH) {
            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(1.6F + this.data.offsetY(), 0.05F, 0.0F);
            GL11.glRotatef(getDeathMaxRotation(player), 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
        } else if (this.data.animation == EnumAnimation.CRAWLING) {
            GL11.glTranslatef(0.0F, 0.2F, 0.0F);
            super.rotateCorpse(par1EntityLiving, par2, par3, par4);
            GL11.glTranslatef(0.0F, 0.0F, 1.5F);
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
        } else {
            super.rotateCorpse(par1EntityLiving, par2, par3, par4);
        }
    }

    public void renderHelmet(EntityPlayer entityPlayer) {
        ItemStack itemstack = entityPlayer.inventory.armorItemInSlot(3);
        if (itemstack == null)
            return;
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, this.data.getBodyY(), 0.0F);

        ModelMPM playerModel = modelBipedMainNewFormat;
        playerModel.bipedHead.postRender(0.0625F);

        GL11.glScalef(this.data.head.scaleX, this.data.head.scaleY, this.data.head.scaleZ);


        if ((itemstack.getItem() instanceof ItemBlock)) {
            IItemRenderer customRenderer = MinecraftForgeClient.getItemRenderer(itemstack, IItemRenderer.ItemRenderType.EQUIPPED);
            boolean is3D = (customRenderer != null) && (customRenderer.shouldUseRenderHelper(IItemRenderer.ItemRenderType.EQUIPPED, itemstack, IItemRenderer.ItemRendererHelper.BLOCK_3D));

            if ((is3D) || (RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack.getItem()).getRenderType()))) {
                float f1 = 0.625F;
                GL11.glTranslatef(0.0F, -0.25F, 0.0F);
                GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                GL11.glScalef(f1, -f1, -f1);
            }

            this.renderManager.itemRenderer.renderItem(entityPlayer, itemstack, 0);
        } else if (itemstack.getItem() == Items.skull) {
            float f1 = 1.0625F;
            GL11.glScalef(f1, -f1, -f1);
            GameProfile gameprofile = null;

            if (itemstack.hasTagCompound()) {
                NBTTagCompound nbttagcompound = itemstack.getTagCompound();

                if (nbttagcompound.hasKey("SkullOwner", 10)) {
                    gameprofile = NBTUtil.func_152459_a(nbttagcompound.getCompoundTag("SkullOwner"));
                } else if ((nbttagcompound.hasKey("SkullOwner", 8)) && (!StringUtils.isNullOrEmpty(nbttagcompound.getString("SkullOwner")))) {
                    gameprofile = new GameProfile((UUID) null, nbttagcompound.getString("SkullOwner"));
                }
            }

            TileEntitySkullRenderer.field_147536_b.func_152674_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, itemstack.getItemDamage(), gameprofile);
        }

        GL11.glPopMatrix();
    }

    public void renderBackitem(EntityPlayer player) {
        ItemStack itemstack = this.data.backItem;
        if ((itemstack == null) || (ItemStack.areItemStacksEqual(itemstack, player.inventory.getCurrentItem())))
            return;
        Block block = null;
        if ((itemstack.getItem() instanceof ItemBlock)) {
            block = Block.getBlockFromItem(itemstack.getItem());
        }
        if ((itemstack.getItemSpriteNumber() == 0) && (block != null) && (RenderBlocks.renderItemIn3d(block.getRenderType())))
            return;
        GL11.glPushMatrix();
        EntityItem entity = new EntityItem(player.worldObj);
        entity.hoverStart = 0.0F;
        entity.rotationYaw = 0.0F;
        entity.setEntityItemStack(itemstack);

        if (this.data.animation == EnumAnimation.DANCING) {
            float dancing = player.ticksExisted / 4.0F;
            GL11.glTranslatef((float) Math.sin(dancing) * 0.015F, 0.0F, 0.0F);
        }

        GL11.glTranslatef(0.0F, this.data.getBodyY(), 0.14299999F * this.data.body.scaleZ);

        ModelMPM playerModel = modelBipedMainNewFormat;
        playerModel.bipedBody.postRender(0.065F);

        if (itemstack.getItem() == Items.bow) {
            GL11.glTranslatef(0.0F, -0.195F, 0.0F);
            GL11.glScalef(1.7F, 1.7F, 1.7F);
        }
        if (itemstack.getItem().isFull3D()) {
            GL11.glScalef(1.7F, 1.7F, 1.7F);
        } else {
            GL11.glTranslatef(0.0F, 0.45499998F, 0.0F);
            GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
        }
        boolean isFancy = this.renderManager.options.fancyGraphics;
        this.renderManager.options.fancyGraphics = true;

        int stack = itemstack.stackSize;
        itemstack.stackSize = 1;

        RenderItem render = (RenderItem) RenderManager.instance.getEntityRenderObject(entity);
        render.doRender(entity, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
        GL11.glPopMatrix();

        this.renderManager.options.fancyGraphics = isFancy;
        itemstack.stackSize = stack;
    }

    public void setEntity(EntityLivingBase entity) {
        ModelBase model = null;
        this.renderEntity = null;
        this.entity = entity;
        if (entity != null) {
            this.renderEntity = ((RendererLivingEntity) RenderManager.instance.getEntityRenderObject(entity));
            model = MPMRendererHelper.getMainModel(this.renderEntity);
            this.renderPassModel = this.renderpass;
            this.renderpass.renderer = this.renderEntity;
            this.renderpass.entity = entity;
        }

        ModelMPM playerModel = modelBipedMainNewFormat;
        playerModel.entityModel = (this.modelArmorChestplate.entityModel = this.modelArmor.entityModel = model);
        playerModel.entity = (this.modelArmorChestplate.entity = this.modelArmor.entity = entity);
    }

    protected void renderEquippedItems(EntityLivingBase entityliving, float f) {
        if (this.renderEntity != null) {
            MPMRendererHelper.renderEquippedItems(this.entity, f, this.renderEntity);
        } else {
            super.renderEquippedItems(entityliving, f);
        }
    }

    protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3) {
        if (this.renderEntity != null) {
            if (this.renderPassModel != null)
                this.renderPassModel.isChild = this.entity.isChild();
            return MPMRendererHelper.shouldRenderPass(this.entity, par2, par3, this.renderEntity);
        }
        return shouldRenderPass((AbstractClientPlayer) par1EntityLivingBase, par2, par3);
    }

    protected void preRenderCallback(EntityLivingBase entityliving, float f) {
        if (this.renderEntity != null) {
            MPMRendererHelper.preRenderCallback(this.entity, f, this.renderEntity);
        } else {
            super.preRenderCallback(entityliving, f);
        }
    }

    protected float handleRotationFloat(EntityLivingBase par1EntityLivingBase, float par2) {
        if (this.renderEntity != null) {
            return MPMRendererHelper.handleRotationFloat(this.entity, par2, this.renderEntity);
        }
        return super.handleRotationFloat(par1EntityLivingBase, par2);
    }

    protected ResourceLocation getEntityTexture(AbstractClientPlayer player) {
        if (this.data.url != null && !this.data.url.isEmpty())
            return player.getLocationSkin();
        else
            return MPMRendererHelper.getResource(player, this.renderEntity, this.entity);
    }
}
