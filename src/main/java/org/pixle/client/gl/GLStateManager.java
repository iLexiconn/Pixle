package org.pixle.client.gl;

import org.lwjgl.opengl.GL11;

public class GLStateManager {
    private static BooleanState blendState = new BooleanState(GL11.GL_BLEND);
    private static ColorState colorState = new ColorState();
    private static BooleanState textureState = new BooleanState(GL11.GL_TEXTURE_2D);
    private static ScaleState scaleState = new ScaleState();
    private static BooleanState rescaleNormalState = new BooleanState(0x803A);

    public static void enableBlend() {
        blendState.setEnabled();
    }

    public static void disableBlend() {
        blendState.setDisabled();
    }

    public static void setColor(int hex) {
        setColor(((hex & 0xFF0000) >> 16) / 255.0F, ((hex & 0xFF00) >> 8) / 255.0F, (hex & 0xFF) / 255.0F);
    }

    public static void setColor(float red, float green, float blue) {
        if (red != colorState.getRed() && green != colorState.getGreen() && blue != colorState.getBlue()) {
            colorState = new ColorState(red, green, blue);
            GL11.glColor3f(colorState.getRed(), colorState.getGreen(), colorState.getBlue());
        }
    }

    public static void enableColor() {
        colorState.setEnabled();
    }

    public static void disableColor() {
        colorState.setDisabled();
    }

    public static void enableTexture() {
        textureState.setEnabled();
    }

    public static void disableTexture() {
        textureState.setDisabled();
    }

    public static void disableRescaleNormal() {
        rescaleNormalState.setDisabled();
    }

    public static void enableRescaleNormal() {
        rescaleNormalState.setEnabled();
    }

    public static void scale(double x, double y) {
        scaleState.setState(x, y);
    }

    public static void scale(float x, float y) {
        scale((double) x, (double) y);
    }

    public static void startDrawingQuads() {
        GL11.glBegin(GL11.GL_QUADS);
    }

    public static void end() {
        GL11.glEnd();
    }

    public static void pushMatrix() {
        GL11.glPushMatrix();
    }

    public static void popMatrix() {
        GL11.glPopMatrix();
    }

    static class BooleanState {
        private int capability;
        private Boolean currentState;

        public BooleanState(int capability) {
            this.capability = capability;
        }

        public void setEnabled() {
            setState(true);
        }

        public void setDisabled() {
            setState(false);
        }

        private void setState(boolean state) {
            if (currentState == null || state != currentState) {
                currentState = state;
                if (state) {
                    GL11.glEnable(capability);
                } else {
                    GL11.glDisable(capability);
                }
            }
        }
    }

    static class ColorState extends BooleanState {
        private float red;
        private float green;
        private float blue;

        public ColorState() {
            this(-1.0F, -1.0F, -1.0F);
        }

        public ColorState(float red, float green, float blue) {
            super(GL11.GL_COLOR);
            this.red = red;
            this.green = green;
            this.blue = blue;
        }

        public float getRed() {
            return red;
        }

        public float getGreen() {
            return green;
        }

        public float getBlue() {
            return blue;
        }
    }

    static class ScaleState {
        private double x;
        private double y;

        public ScaleState() {
            this(1.0, 1.0);
        }

        public ScaleState(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public void setState(double x, double y) {
            if (x != getX() || y != getY()) {
                GL11.glScaled(x, y, 1.0);
            }
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }
    }
}