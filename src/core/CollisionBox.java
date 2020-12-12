package core;

import java.awt.Rectangle;

/*
* Kotak Buat Check collision antar object
*
* */

public class CollisionBox {
    private Rectangle bounds;

    public CollisionBox(Rectangle bounds){
        this.bounds = bounds;
    }

    //apakah bertabrakan dengan object lain (saling interseksi)
    public boolean collideWith(CollisionBox other){
        return bounds.intersects(other.bounds);
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
