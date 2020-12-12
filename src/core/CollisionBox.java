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

    //helper method
    public static CollisionBox of(Position position, Size size) {
        return new CollisionBox(
                new Rectangle(
                        position.intX(),
                        position.intY(),
                        size.getWidth(),
                        size.getHeight()
                )
        );
    }

    //apakah bertabrakan dengan object lain (saling interseksi)
    public boolean collideWith(CollisionBox other){
        return bounds.intersects(other.bounds);
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
