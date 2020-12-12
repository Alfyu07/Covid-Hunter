package ui;

import core.Position;
import core.Size;
import game.state.State;
import gfx.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

/*
* bisa jadi container atau jadi component sendiri
*
* */
public class UIContainer extends UIComponent{

    private Color backgroundColor;

    public UIContainer() {
        super();
        this.backgroundColor = Color.red;
    }

    private void calculateSize(){
        size = new Size(padding.getHorizontal(), padding.getVertical());
    }
    private void calculatePosition(){
        position = new Position(margin.getLeft(), margin.getTop());
    }

    @Override
    public Image getSrite() {
        BufferedImage Image = (BufferedImage) ImageUtils.createCompatibleImage(size,ImageUtils.ALPHA_BIT_MASKED);
        Graphics2D g = Image.createGraphics();
        g.setColor(backgroundColor);
        g.fillRect(0,0, size.getWidth(), size.getHeight());

        g.dispose();

        return Image;
    }

    @Override
    public void update(State state) {
        calculateSize();
        calculatePosition();
    }
}
