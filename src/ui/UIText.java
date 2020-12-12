package ui;

import core.Size;
import game.state.State;
import gfx.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UIText extends UIComponent{
    private String text;
    private int fontSize;
    private int fontStyle;
    private String fontFamily;
    private Color color;

    private boolean dropShadow;
    private int dropShadowOffSet;
    private Color shadowColor;

    private Font font;

    public UIText(String text) {
        this.text = text;
        this.fontSize = 24;
        this.fontStyle = Font.PLAIN;
        this.fontFamily = "Press Start 2P";
        this.color = Color.WHITE;
        this.dropShadow = true;
        this.dropShadowOffSet = 2;

        this.shadowColor = new Color(140,140,140);
    }

    @Override
    public Image getSprite() {
        BufferedImage image = (BufferedImage) ImageUtils.createCompatibleImage(size,ImageUtils.ALPHA_BIT_MASKED);
        Graphics2D g = image.createGraphics();
        g.setFont(font);

        if(dropShadow){
            g.setColor(shadowColor);
            g.drawString(text, padding.getLeft() + dropShadowOffSet, fontSize + padding.getTop() + dropShadowOffSet);
        }

        g.setColor(color);
        g.drawString(text, padding.getLeft(), fontSize + padding.getTop());
        g.dispose();

        return image;
    }

    @Override
    public void update(State state) {
        createFont();
        calculateSize();
    }

    private void createFont() {
        font = new Font(fontFamily,fontStyle, fontSize);
    }

    private void calculateSize() {
        FontMetrics fontMetrics = new Canvas().getFontMetrics(font);
        size = new Size(
                fontMetrics.stringWidth(text) + padding.getHorizontal(),
                fontMetrics.getHeight() + padding.getVertical()
        );
    }
}
