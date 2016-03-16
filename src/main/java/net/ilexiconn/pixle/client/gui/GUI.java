package net.ilexiconn.pixle.client.gui;

import net.ilexiconn.pixle.client.PixleClient;
import net.ilexiconn.pixle.client.gui.component.GUIComponent;

import java.util.ArrayList;
import java.util.List;

public abstract class GUI {
    private List<GUIComponent> components = new ArrayList<GUIComponent>();

    public abstract void updateComponents();

    public void render(int mouseX, int mouseY) {
        for (GUIComponent component : components) {
            component.render(mouseX, mouseY);
        }
    }

    public void keyTyped(char c, int keyCode) {
        for (GUIComponent component : components) {
            component.keyPressed(c, keyCode);
        }
    }

    public void mouseClicked(int mouseX, int mouseY) {
        for (GUIComponent component : components) {
            component.mouseClicked(mouseX, mouseY);
        }
    }

    protected void addComponenent(GUIComponent component) {
        components.add(component);
    }

    public void clearComponents() {
        components.clear();
    }

    public void close() {
        PixleClient.INSTANCE.closeGUI(this);
    }
}
