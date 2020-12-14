package ui.clickable;

import core.Size;
import gfx.ImageUtils;
import state.State;
import ui.Spacing;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UISlider extends UIClickable{

    private double value;
    private double min;
    private double max;

    public UISlider(double min, double max) {
        this.min = min;
        this.max = max;
        this.value = max;
        this.margin = new Spacing(0,0,10,0);
        //tambah size dari slider
        this.size = new Size(360,10);
    }


    @Override
    public Image getSprite() {
        BufferedImage sprite = (BufferedImage) ImageUtils.createCompatibleImage(size, ImageUtils.ALPHA_OPAQUE);
        Graphics2D g = sprite.createGraphics();

        g.setColor(Color.GRAY);
        g.fillRect(0,0, size.getWidth(), size.getHeight());

        g.setColor(Color.WHITE);
        g.fillRect(0,0, getPixelOfCurrentValue(), size.getHeight());

        g.dispose();
        return sprite;
    }

    @Override
    protected void onClick(State state) { }

    @Override
    protected void onFocus(State state) { }

    @Override
    protected void onDrag(State state) {
        this.value = getValueAt(state.getInput().getMousePosition().getX());

    }

    private int getPixelOfCurrentValue() {
        double range = max - min;
        double persentage = value - min;

        return (int) ((persentage / range) * size.getWidth());
    }

    private double getValueAt(double xPosition) {
        double positionOnSlider = xPosition - absolutePosition.getX();
        double persentage = positionOnSlider / size.getWidth();
        double range = max -  min;

        return min  + range * persentage;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
