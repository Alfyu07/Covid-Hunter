package core;

import java.awt.Rectangle;

public class CollisionBox {
    private Rectangle bounds;

    public CollisionBox(Rectangle bounds){
        this.bounds = bounds;
    }

    public boolean collideWith(CollisionBox other){
        return bounds.intersects(other.bounds);
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
