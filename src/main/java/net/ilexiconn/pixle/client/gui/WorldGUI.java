package net.ilexiconn.pixle.client.gui;

import net.ilexiconn.pixle.client.PixleClient;
import net.ilexiconn.pixle.client.gl.GLStateManager;
import net.ilexiconn.pixle.client.render.RenderHelper;
import net.ilexiconn.pixle.client.render.RenderingRegistry;
import net.ilexiconn.pixle.client.render.entity.IEntityRenderer;
import net.ilexiconn.pixle.world.World;
import net.ilexiconn.pixle.world.entity.Entity;
import net.ilexiconn.pixle.world.entity.PlayerEntity;
import net.ilexiconn.pixle.world.pixel.Pixel;
import net.ilexiconn.pixle.world.region.Region;
import org.lwjgl.opengl.Display;

public class WorldGUI extends GUI {
    public WorldGUI(PixleClient pixle) {
        super(pixle);
    }

    @Override
    public void render() {
        World world = pixle.getWorld();
        PlayerEntity player = pixle.getPlayer();

        int pixelSize = World.PIXEL_SIZE;
        int regionCount = (Display.getWidth() / (pixelSize * 16)) + 1;
        int playerRegionX = world.getRegionX((int) player.posX);

        GLStateManager.setColor(0x0094FF);

        int centerY = (Display.getHeight() / pixelSize) / 2;

        RenderHelper.drawRect(0, 0, Display.getWidth(), Display.getHeight() - (int) ((centerY - player.posY - 1) * pixelSize));

        for (int regionX = 0; regionX < regionCount; regionX++) {
            Region region = world.getRegion((playerRegionX + regionX) - (regionCount / 2));
            for (int y = 0; y < 256; y++) {
                double relativeY = y - player.posY;
                if (relativeY >= -centerY - 1 && relativeY <= centerY + 1) {
                    for (int x = 0; x < 16; x++) {
                        Pixel pixel = region.getPixel(x, y);
                        if (pixel != null) {
                            GLStateManager.setColor(pixel.getMaterial().getColor());
                            RenderHelper.drawRect((int) (((x + (regionX * 16)) - player.posX) * pixelSize), (Display.getHeight() / 2) - (int) (relativeY * pixelSize), pixelSize, pixelSize);
                        }
                    }
                }
            }
        }
        for (Entity entity : world.getEntities()) {
            IEntityRenderer entityRenderer = RenderingRegistry.getEntityRenderer(entity.getClass());
            if (entityRenderer != null) {
                entityRenderer.render(entity, (Display.getWidth() / 2) - (int) ((entity.posX - player.posX) * pixelSize), (Display.getHeight() / 2) - (int) ((entity.posY - player.posY) * pixelSize), world, (float) pixle.getDelta());
            }
        }
    }
}
