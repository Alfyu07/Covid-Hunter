package entities;

import core.CollisionBox;
import core.Position;
import core.Size;
import display.Camera;
import game.state.State;
import java.awt.Image;

public abstract class GameObject {
    protected Position position;
    protected Position renderOffset;
    protected Size size;

    protected GameObject parent;
    protected int renderOrder;
    public GameObject() {
        position = new Position(0,0);
        size = new Size(64,64);
        renderOffset = new Position(0,0);
        renderOrder = 5;
    }

    public abstract void update(State state);
    public abstract Image getSprite();


    //method untuk mengambil collision box
    public abstract CollisionBox getCollisionBox();
    //cek apakah interseksi di yang lain.
    public boolean collidesWith(GameObject other){
        return getCollisionBox().collideWith(other.getCollisionBox());
    }

    public Position getPosition() {
        Position finalPosition = Position.copyOf(position);

        if(parent != null){
            finalPosition.add(parent.getPosition());
        }

        return finalPosition;
    }

    public Size getSize() {
        return size;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setParent(GameObject parent) {
        this.parent = parent;
    }

    public Position getRenderPosition(Camera camera) {
        return new Position(
            getPosition().getX() - camera.getPosition().getX() - renderOffset.getX(),
            getPosition().getY() - camera.getPosition().getY() - renderOffset.getY()
        );
    }

    public int getRenderOrder() {
        return renderOrder;
    }
}
