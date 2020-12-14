package entities;

import core.CollisionBox;
import core.Position;
import core.Size;
import state.State;
import gfx.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SelectionCircle extends GameObject{

    private Color color;
    private BufferedImage sprite;

    public SelectionCircle() {
        color = new Color(0,255,255);
        size = new Size(20,  16);
        renderOffset = new Position(size.getWidth() / 2, size.getHeight());
        renderOrder = 4;
        collisionBoxOffset = renderOffset;
        initializeSprite();

    }
    private void initializeSprite(){
        sprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
        Graphics2D g = sprite.createGraphics();

        g.setColor(color);
        g.setStroke(new BasicStroke(2));
        g.drawOval(0,0,size.getWidth(), size.getHeight());


        g.dispose();
    }
    @Override
    public void update(State state) {

    }

    @Override
    public Image getSprite() {
        return parent != null ? sprite : null;
    }

    @Override
    public CollisionBox getCollisionBox() {
        Position position = getPosition();
        position.subtract(collisionBoxOffset);
        return CollisionBox.of(position, getSize());

    }
}
