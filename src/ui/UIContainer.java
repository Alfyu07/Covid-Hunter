package ui;

import core.Position;
import core.Size;
import game.state.State;
import gfx.ImageUtils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/*
* bisa jadi container atau jadi component sendiri
*
* */
public abstract class UIContainer extends UIComponent{

    protected Color backgroundColor;

    protected List<UIComponent> child;

    public UIContainer() {
        super();
        this.backgroundColor = Color.red;
        margin = new Spacing(5);
        padding = new Spacing(5);
        child = new ArrayList<>();
        calculateSize();
        calculatePosition();
    }

    protected abstract Size calculateContentSize();
    protected abstract void calculatedContentPosition();
    private void calculateSize(){
        Size calculatedContentSize = calculateContentSize();
        size = new Size(padding.getHorizontal() + calculatedContentSize.getWidth(),
                padding.getVertical() + calculatedContentSize.getHeight());
    }
    private void calculatePosition(){
        position = new Position(margin.getLeft(), margin.getTop());
        calculatedContentPosition();
    }

    @Override
    public Image getSprite() {
        BufferedImage Image = (BufferedImage) ImageUtils.createCompatibleImage(size,ImageUtils.ALPHA_BIT_MASKED);
        Graphics2D g = Image.createGraphics();
        g.setColor(backgroundColor);
        g.fillRect(0,0, size.getWidth(), size.getHeight());



        for(UIComponent uiComponent: child){
            g.drawImage(
                    uiComponent.getSprite(),
                    uiComponent.getPosition().intX(),
                    uiComponent.getPosition().intY(),
                    null
            );
        }
        g.dispose();
        return Image;
    }

    @Override
    public void update(State state) {
        child.forEach(uiComponent -> uiComponent.update(state));
        calculateSize();
        calculatePosition();
    }

    public void addUIComponent(UIComponent uiComponent){
        child.add(uiComponent);

    }

    public void setBackgroundColor(Color color){
        backgroundColor = color;
    }
}
