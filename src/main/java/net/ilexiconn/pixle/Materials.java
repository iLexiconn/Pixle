package net.ilexiconn.pixle;

import net.ilexiconn.pixle.world.pixel.GrassMaterial;
import net.ilexiconn.pixle.world.pixel.Material;

public class Materials {
    public static Material grass;

    public static void init() {
        grass = new GrassMaterial();
    }
}