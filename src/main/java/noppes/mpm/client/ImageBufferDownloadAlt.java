package noppes.mpm.client;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.WritableRaster;
import net.minecraft.client.renderer.ImageBufferDownload;

public class ImageBufferDownloadAlt
  extends ImageBufferDownload
{
  private int[] imageData;
  private int imageWidth;
  private int imageHeight;
  
  public BufferedImage parseUserSkin(BufferedImage bufferedimage)
  {
    this.imageWidth = bufferedimage.getWidth(null);
    this.imageHeight = (this.imageWidth / 2);
    
    BufferedImage bufferedimage1 = new BufferedImage(this.imageWidth, this.imageHeight, 2);
    Graphics g = bufferedimage1.getGraphics();
    g.drawImage(bufferedimage, 0, 0, null);
    g.dispose();
    this.imageData = ((DataBufferInt)bufferedimage1.getRaster().getDataBuffer()).getData();
    setAreaTransparent(this.imageWidth / 2, 0, this.imageWidth, this.imageHeight / 2);
    return bufferedimage1;
  }
  




  private void setAreaTransparent(int par1, int par2, int par3, int par4)
  {
    if (!hasTransparency(par1, par2, par3, par4))
    {
      for (int i1 = par1; i1 < par3; i1++)
      {
        for (int j1 = par2; j1 < par4; j1++)
        {
          this.imageData[(i1 + j1 * this.imageWidth)] &= 0xFFFFFF;
        }
      }
    }
  }
  



  private boolean hasTransparency(int par1, int par2, int par3, int par4)
  {
    for (int i1 = par1; i1 < par3; i1++)
    {
      for (int j1 = par2; j1 < par4; j1++)
      {
        int k1 = this.imageData[(i1 + j1 * this.imageWidth)];
        
        if ((k1 >> 24 & 0xFF) < 128)
        {
          return true;
        }
      }
    }
    
    return false;
  }
}
