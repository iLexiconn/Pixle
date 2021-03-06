package org.pixle.util;

import org.pixle.entity.Entity;

public class EntityBounds extends Bounds {
    private float sizeX;
    private float sizeY;
    private Entity entity;

    public EntityBounds(Entity entity, float sizeX, float sizeY) {
        this.entity = entity;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    @Override
    public float getMaxX() {
        return (float) (entity.posX + sizeX);
    }

    @Override
    public float getMinX() {
        return (float) entity.posX;
    }

    @Override
    public float getMaxY() {
        return (float) (entity.posY + sizeY);
    }

    @Override
    public float getMinY() {
        return (float) entity.posY;
    }

    @Override
    public float getSizeX() {
        return sizeX;
    }

    @Override
    public float getSizeY() {
        return sizeY;
    }

    public Entity getEntity() {
        return entity;
    }
}
