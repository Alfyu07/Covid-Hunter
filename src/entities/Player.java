package entities;

import controller.Controller;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Player extends MovingEntity {

    public Player(Controller controller){
        super(controller);
    }
    @Override
    public void update() {
        super.update();
    }

    @Override
    public Image getSprite() {
        BufferedImage image = new BufferedImage(size.getWidth(), size.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        g.setColor(Color.green);
        g.fillRect(0,0,size.getWidth(), size.getHeight());

        g.dispose();
        return image;
    }
}
