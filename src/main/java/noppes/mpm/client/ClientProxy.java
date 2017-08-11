package noppes.mpm.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.network.FMLEventChannel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import noppes.mpm.CommonProxy;
import noppes.mpm.MorePlayerModels;








public class ClientProxy
  extends CommonProxy
{
  public static KeyBinding Screen;
  public static KeyBinding Sleep;
  public static KeyBinding Sit;
  public static KeyBinding Dance;
  public static KeyBinding Hug;
  public static KeyBinding Crawl;
  
  public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
  {
    return null;
  }
  
  public void load()
  {
    MorePlayerModels.Channel.register(new PacketHandlerClient());
    new PresetController(MorePlayerModels.instance.dir);
    
    ClientRegistry.registerKeyBinding(Screen = new KeyBinding("CharacterScreen", 88, "key.categories.gameplay"));
    ClientRegistry.registerKeyBinding(Sleep = new KeyBinding("MPM 1", 44, "key.categories.gameplay"));
    ClientRegistry.registerKeyBinding(Sit = new KeyBinding("MPM 2", 45, "key.categories.gameplay"));
    ClientRegistry.registerKeyBinding(Dance = new KeyBinding("MPM 3", 46, "key.categories.gameplay"));
    ClientRegistry.registerKeyBinding(Hug = new KeyBinding("MPM 4", 47, "key.categories.gameplay"));
    ClientRegistry.registerKeyBinding(Crawl = new KeyBinding("MPM 5", 48, "key.categories.gameplay"));
    

    RenderingRegistry.registerEntityRenderingHandler(EntityPlayer.class, RenderEvent.renderer);
    

    FMLCommonHandler.instance().bus().register(new ClientEventHandler());
    MinecraftForge.EVENT_BUS.register(new RenderEvent());
  }
  
  public static void bindTexture(ResourceLocation location) {
    if (location == null)
      return;
    TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
    try {
      texturemanager.bindTexture(location);
    }
    catch (ReportedException ex) {}catch (NullPointerException e) {}
  }
}
