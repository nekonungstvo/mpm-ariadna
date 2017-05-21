package noppes.mpm.client.gui.util;

import java.util.HashSet;
import java.util.Vector;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.Tessellator;









public class GuiNPCStringSlot
  extends GuiSlot
{
  private Vector<String> list;
  public String selected;
  public HashSet<String> selectedList;
  private boolean multiSelect;
  private GuiScreen parent;
  private GuiListActionListener listener;
  public int size;
  
  public GuiNPCStringSlot(Vector<String> list, GuiScreen parent, boolean multiSelect, int size)
  {
    super(Minecraft.getMinecraft(), parent.width, parent.height, 32, parent.height - 64, size);
    this.selectedList = new HashSet();
    this.parent = parent;
    this.list = list;
    this.multiSelect = multiSelect;
    this.size = size;
    if ((parent instanceof GuiListActionListener)) {
      this.listener = ((GuiListActionListener)parent);
    }
  }
  
  protected int getSize() { return this.list.size(); }
  
  private long prevTime = 0L;
  








  protected void elementClicked(int i, boolean flag, int var3, int var4)
  {
    long time = System.currentTimeMillis();
    if ((this.listener != null) && (this.selected != null) && (this.selected.equals(this.list.get(i))) && (time - this.prevTime < 400L))
      this.listener.doubleClicked();
    this.selected = ((String)this.list.get(i));
    if (this.selectedList.contains(this.selected)) {
      this.selectedList.remove(this.selected);
    } else
      this.selectedList.add(this.selected);
    if (this.listener != null)
      this.listener.elementClicked();
    this.prevTime = time;
  }
  
  protected boolean isSelected(int i)
  {
    if (!this.multiSelect) {
      if (this.selected == null)
        return false;
      return this.selected.equals(this.list.get(i));
    }
    
    return this.selectedList.contains(this.list.get(i));
  }
  

  protected int getContentHeight()
  {
    return this.list.size() * this.size;
  }
  
  protected void drawBackground()
  {
    this.parent.drawDefaultBackground();
  }
  
  protected void drawSlot(int i, int j, int k, int l, Tessellator tessellator, int var6, int var7)
  {
    if (i >= this.list.size())
      return;
    String s = (String)this.list.get(i);
    this.parent.drawString(Minecraft.getMinecraft().fontRenderer, s, j + 50, k + 3, 16777215);
  }
  
  public void clear() {
    this.list.clear();
  }
  
  public void setList(Vector<String> list) { this.list = list; }
}
