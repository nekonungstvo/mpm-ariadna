package noppes.mpm.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.IChatComponent;
import org.lwjgl.opengl.GL11;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatMessages {
    private static Map<String, ChatMessages> users = new java.util.Hashtable();
    private static Pattern[] patterns = {
            Pattern.compile("^<+([a-zA-z0-9_]{2,16})>[:]? (.*)"),
            Pattern.compile("^\\[.*[\\]]{1,16}[^a-zA-z0-9]?([a-zA-z0-9_]{2,16})[:]? (.*)"),
            Pattern.compile("^[a-zA-z0-9_]{2,10}[^a-zA-z0-9]([a-zA-z0-9_]{2,16})[:]? (.*)")};
    private Map<Long, TextBlockClient> messages = new TreeMap();
    private int boxLength = 46;
    private float scale = 0.5F;
    private String lastMessage = "";
    private long lastMessageTime = 0L;

    public static void drawRect(int par0, int par1, int par2, int par3, int par4, double par5) {
        if (par0 < par2) {
            int j1 = par0;
            par0 = par2;
            par2 = j1;
        }

        if (par1 < par3) {
            int j1 = par1;
            par1 = par3;
            par3 = j1;
        }

        float f = (par4 >> 24 & 0xFF) / 255.0F;
        float f1 = (par4 >> 16 & 0xFF) / 255.0F;
        float f2 = (par4 >> 8 & 0xFF) / 255.0F;
        float f3 = (par4 & 0xFF) / 255.0F;
        Tessellator tessellator = Tessellator.instance;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(f1, f2, f3, f);
        tessellator.startDrawingQuads();
        tessellator.addVertex(par0, par3, par5);
        tessellator.addVertex(par2, par3, par5);
        tessellator.addVertex(par2, par1, par5);
        tessellator.addVertex(par0, par1, par5);
        tessellator.draw();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }

    public static ChatMessages getChatMessages(String username) {
        if (users.containsKey(username)) {
            return (ChatMessages) users.get(username);
        }
        ChatMessages chat = new ChatMessages();
        users.put(username, chat);
        return chat;
    }

    public static void parseMessage(String toParse) {
        toParse = toParse.replaceAll("§.", "");
        for (Pattern pattern : patterns) {
            Matcher m = pattern.matcher(toParse);
            if (m.find()) {
                String username = m.group(1);
                if (validPlayer(username)) {
                    String message = m.group(2);
                    getChatMessages(username).addMessage(message);
                    return;
                }
            }
        }
    }

    public static void test() {
        test("<Sirnoppes01> :)", "Sirnoppes01: :)");
        test("<Sirnoppes01> hey", "Sirnoppes01: hey");
        test("<Sir_noppes> hey", "Sir_noppes: hey");
        test("<Sirnoppes>: hey", "Sirnoppes: hey");
        test("[member]Sirnoppes: hey", "Sirnoppes: hey");
        test("[member]Sirnoppes01: hey", "Sirnoppes01: hey");
        test("[member]Sir_noppes: hey", "Sir_noppes: hey");
        test("[member] Sirnoppes: hey", "Sirnoppes: hey");
        test("[g][member]Sirnoppes: hey", "Sirnoppes: hey");
        test("[g] [member]Sirnoppes: hey", "Sirnoppes: hey");
        test("[g] [member]-Sirnoppes: hey", "Sirnoppes: hey");
        test("[Player755: Teleported Player755 to Player885]", "");
        test("member Sirnoppes: hey", "Sirnoppes: hey");
        test("member-Sirnoppes: hey", "Sirnoppes: hey");
        test("member: Sirnoppes: hey", "");
    }

    private static void test(String toParse, String result) {
        for (Pattern pattern : patterns) {
            Matcher m = pattern.matcher(toParse);
            if (m.find()) {
                String username = m.group(1);
                String message = m.group(2);
                if ((message != null) && (username != null)) {
                    if (result.isEmpty()) {
                        System.err.println("failed: " + toParse + " - " + username + ": " + message);
                        return;
                    }
                    if ((username + ": " + message).equals(result)) {
                        System.out.println("succes: " + toParse);
                        return;
                    }
                }
            }
        }
        if (result.isEmpty()) {
            System.out.println("succes: " + toParse);
        } else
            System.err.println("failed: " + toParse);
    }

    private static boolean validPlayer(String username) {
        return Minecraft.getMinecraft().theWorld.getPlayerEntityByName(username) != null;
    }

    public void addMessage(String message) {
        if (!noppes.mpm.MorePlayerModels.EnableChatBubbles)
            return;
        long time = System.currentTimeMillis();
        if ((message.equals(this.lastMessage)) && (this.lastMessageTime + 1000L > time)) {
            return;
        }
        Map<Long, TextBlockClient> messages = new TreeMap(this.messages);
        messages.put(Long.valueOf(time), new TextBlockClient(message, this.boxLength * 4));

        if (messages.size() > 3) {
            messages.remove(messages.keySet().iterator().next());
        }
        this.messages = messages;
        this.lastMessage = message;
        this.lastMessageTime = time;
    }

    public void renderMessages(double par3, double par5, double par7) {
        Map<Long, TextBlockClient> messages = getMessages();
        if (messages.isEmpty())
            return;
        FontRenderer font = Minecraft.getMinecraft().fontRenderer;
        float var13 = 1.6F;
        float var14 = 0.016666668F * var13;
        GL11.glPushMatrix();
        int size = 0;
        for (TextBlockClient block : messages.values()) {
            size += block.lines.size();
        }
        int textYSize = (int) (size * font.FONT_HEIGHT * this.scale);
        GL11.glTranslatef((float) par3 + 0.0F, (float) par5 + textYSize * var14, (float) par7);

        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-RenderManager.instance.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(RenderManager.instance.playerViewX, 1.0F, 0.0F, 0.0F);
        GL11.glScalef(-var14, -var14, var14);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(3008);
        GL11.glDepthMask(true);
        GL11.glEnable(3042);
        GL11.glDepthFunc(515);
        net.minecraft.client.renderer.OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glDisable(3553);

        drawRect(-this.boxLength - 2, -2, this.boxLength + 2, textYSize + 1, -1140850689, 0.11D);

        drawRect(-this.boxLength - 1, -3, this.boxLength + 1, -2, -16777216, 0.1D);
        drawRect(-this.boxLength - 1, textYSize + 2, -1, textYSize + 1, -16777216, 0.1D);
        drawRect(3, textYSize + 2, this.boxLength + 1, textYSize + 1, -16777216, 0.1D);
        drawRect(-this.boxLength - 3, -1, -this.boxLength - 2, textYSize, -16777216, 0.1D);
        drawRect(this.boxLength + 3, -1, this.boxLength + 2, textYSize, -16777216, 0.1D);

        drawRect(-this.boxLength - 2, -2, -this.boxLength - 1, -1, -16777216, 0.1D);
        drawRect(this.boxLength + 2, -2, this.boxLength + 1, -1, -16777216, 0.1D);
        drawRect(-this.boxLength - 2, textYSize + 1, -this.boxLength - 1, textYSize, -16777216, 0.1D);
        drawRect(this.boxLength + 2, textYSize + 1, this.boxLength + 1, textYSize, -16777216, 0.1D);

        drawRect(0, textYSize + 1, 3, textYSize + 4, -1140850689, 0.11D);
        drawRect(-1, textYSize + 4, 1, textYSize + 5, -1140850689, 0.11D);

        drawRect(-1, textYSize + 1, 0, textYSize + 4, -16777216, 0.1D);
        drawRect(3, textYSize + 1, 4, textYSize + 3, -16777216, 0.1D);
        drawRect(2, textYSize + 3, 3, textYSize + 4, -16777216, 0.1D);
        drawRect(1, textYSize + 4, 2, textYSize + 5, -16777216, 0.1D);
        drawRect(-2, textYSize + 4, -1, textYSize + 5, -16777216, 0.1D);

        drawRect(-2, textYSize + 5, 1, textYSize + 6, -16777216, 0.1D);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);

        GL11.glScalef(this.scale, this.scale, this.scale);
        int index = 0;
        for (TextBlockClient block : messages.values()) {
            for (IChatComponent chat : block.lines) {
                String message = chat.getFormattedText();
                font.drawString(message, -font.getStringWidth(message) / 2, index * font.FONT_HEIGHT, 0);
                index++;
            }
        }
        GL11.glEnable(2896);
        GL11.glDisable(3042);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopMatrix();
        RenderHelper.enableStandardItemLighting();
    }

    private Map<Long, TextBlockClient> getMessages() {
        Map<Long, TextBlockClient> messages = new TreeMap();
        long time = System.currentTimeMillis();
        for (Iterator localIterator = this.messages.keySet().iterator(); localIterator.hasNext(); ) {
            long timestamp = ((Long) localIterator.next()).longValue();
            if (time <= timestamp + 10000L) {
                TextBlockClient message = (TextBlockClient) this.messages.get(Long.valueOf(timestamp));
                messages.put(Long.valueOf(timestamp), message);
            }
        }
        this.messages = messages;
        return messages;
    }
}
