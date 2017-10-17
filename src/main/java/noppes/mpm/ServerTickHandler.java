package noppes.mpm;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraft.server.MinecraftServer;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class ServerTickHandler {
    private String serverName = null;

    @SubscribeEvent
    public void playerLogin(PlayerLoggedInEvent event) {
        if (this.serverName == null) {
            String e = "local";
            MinecraftServer server = MinecraftServer.getServer();
            if (server.isDedicatedServer()) {
                try {
                    e = InetAddress.getByName(server.getServerHostname()).getCanonicalHostName();
                } catch (UnknownHostException e1) {
                    e = MinecraftServer.getServer().getServerHostname();
                }
                if (server.getPort() != 25565)
                    e = e + ":" + server.getPort();
            }
            if ((e == null) || (e.startsWith("192.168")) || (e.contains("127.0.0.1")) || (e.startsWith("localhost")))
                e = "local";
            this.serverName = e;
        }
    }
}
