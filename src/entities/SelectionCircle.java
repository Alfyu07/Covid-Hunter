package entities;

import core.CollisionBox;
import core.Position;
import core.Size;
import game.state.State;
import gfx.ImageUtils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class SelectionCircle extends GameObject{

    private Color color;
    private BufferedImage sprite;

    public SelectionCircle() {
        color = Color.ORANGE;
        size = new Size(32,16);
        renderOffset = new Position(size.getWidth() / 2, size.getHeight());
        initializeSprite();
        renderOrder = 4;

    }
    private void initializeSprite(){
        sprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED);
        Graphics2D g = sprite.createGraphics();

        g.setColor(color);
        g.fillOval(0,0,size.getWidth(), size.getHeight());


        g.dispose();
    }
    @Override
    public void update(State state) {

    }

    @Override
    public Image getSprite() {
        return sprite;
    }

    @Override
    public CollisionBox getCollisionBox() {
        return CollisionBox.of(getPosition(), getSize());

    }
}
