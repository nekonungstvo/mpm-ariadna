package noppes.mpm.client;

import net.minecraft.client.renderer.ImageBufferDownload;
import noppes.mpm.ModelData;

import java.awt.image.BufferedImage;

public class ImageBufferDownloadAlt extends ImageBufferDownload {
    private ModelData data;

    public ImageBufferDownloadAlt(ModelData data) {
        this.data = data;
    }

    @Override
    public BufferedImage parseUserSkin(BufferedImage bufferedImage) {
        if (bufferedImage == null) return null;

        boolean newSkinFormat = bufferedImage.getWidth() == bufferedImage.getHeight();
        if (data.newSkinFormat != newSkinFormat)
            data.reloadBoxes = true;
        data.newSkinFormat = newSkinFormat;

        return bufferedImage;
    }
}
