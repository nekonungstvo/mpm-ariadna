package noppes.mpm.client;

import net.minecraft.client.renderer.ImageBufferDownload;
import noppes.mpm.ModelData;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageBufferDownloadAlt extends ImageBufferDownload {
    private ModelData data;

    public ImageBufferDownloadAlt(ModelData data) {
        this.data = data;
    }

    private void drawFlipped(Graphics2D g2d, BufferedImage image, int x, int y) {
        g2d.drawImage(image, x + image.getWidth(), y, -image.getWidth(), image.getHeight(), null);
    }

    private BufferedImage flipLimb(BufferedImage image) {
        BufferedImage new_image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = new_image.createGraphics();

        BufferedImage top = image.getSubimage(4, 0, 4, 4);
        this.drawFlipped(g2d, top, 4, 0);

        BufferedImage bot = image.getSubimage(8, 0, 4, 4);
        this.drawFlipped(g2d, bot, 8, 0);

        BufferedImage right = image.getSubimage(0, 4, 4, 12);
        this.drawFlipped(g2d, right, 8, 4);

        BufferedImage front = image.getSubimage(4, 4, 4, 12);
        this.drawFlipped(g2d, front, 4, 4);

        BufferedImage left = image.getSubimage(8, 4, 4, 12);
        this.drawFlipped(g2d, left, 0, 4);

        BufferedImage back = image.getSubimage(12, 4, 4, 12);
        this.drawFlipped(g2d, back, 12, 4);

        g2d.dispose();

        return new_image;
    }

    private BufferedImage reformatOldSkin(BufferedImage image) {
        BufferedImage new_image = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = new_image.createGraphics();
        g2d.drawImage(image, 0, 0, null);

        BufferedImage leg = this.flipLimb(image.getSubimage(0, 16, 16, 16));

        g2d.drawImage(leg, 16, 48, null);

        BufferedImage hand = this.flipLimb(image.getSubimage(40, 16, 16, 16));
        g2d.drawImage(hand, 32, 48, null);

        g2d.dispose();

        return new_image;
    }

    @Override
    public BufferedImage parseUserSkin(BufferedImage image) {
        if (image == null) return null;

        // If main skin texture
        boolean newSkinFormat = image.getWidth() == image.getHeight();

        if (!newSkinFormat) {
            image = reformatOldSkin(image);
        }

        return image;
    }
}
