package net.ilexiconn.pixle.client.gui.component;

import net.ilexiconn.pixle.client.PixleClient;
import net.ilexiconn.pixle.client.gl.GLStateManager;
import net.ilexiconn.pixle.client.render.RenderHelper;
import org.newdawn.slick.TrueTypeFont;

public class Button extends GUIComponent {
    protected int width;
    protected int height;
    protected String text;
    protected IButtonActionHandler actionHandler;
    protected IComponentTheme theme;

    public static final IComponentTheme DEFAULT_THEME = new IComponentTheme() {
        @Override
        public int getPrimaryColor(boolean selected) {
            return selected ? 0x666666 : 0x999999;
        }

        @Override
        public int getSecondaryColor(boolean selected) {
            return selected ? 0x404040 : 0x666666;
        }
    };

    public Button(int x, int y, int width, int height, String text, IButtonActionHandler actionHandler, IComponentTheme theme) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.text = text;
        this.actionHandler = actionHandler;
        this.theme = theme;
    }

    public Button(int x, int y, int width, int height, String text, IButtonActionHandler actionHandler) {
        this(x, y, width, height, text, actionHandler, DEFAULT_THEME);
    }

    @Override
    public void render(int mouseX, int mouseY) {
        boolean selected = isMouseOver(mouseX, mouseY);

        GLStateManager.setColor(theme.getPrimaryColor(selected));
        RenderHelper.drawRect(x, y, width, height);
        GLStateManager.setColor(theme.getSecondaryColor(selected));
        RenderHelper.drawOutline(x, y, width, height, 2);

        GLStateManager.enableTexture();
        TrueTypeFont font = PixleClient.INSTANCE.getFontRenderer();
        font.drawString((x + (width / 2)) - (font.getWidth(text) / 2), (y + (height / 2)) - (font.getHeight(text) / 2) + 6, text);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY) {
        if (isMouseOver(mouseX, mouseY)) {
            actionHandler.clicked(this);
        }
    }

    @Override
    public void keyPressed(char c, int key) {
    }

    private boolean isMouseOver(int mouseX, int mouseY) {
        return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }

    public String getText() {
        return text;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public interface IButtonActionHandler {
        void clicked(Button button);
    }
}
