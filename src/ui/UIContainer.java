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
    protected Alignment alignment;
    protected Size windowSize;

    public UIContainer(Size windowSize) {
        super();
        this.windowSize = windowSize;
        alignment = new Alignment(Alignment.Position.CENTER, Alignment.Position.END);
        this.backgroundColor = new Color(0,0,0,0);
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
        int x = padding.getLeft();
        if(alignment.getHorizontal().equals(Alignment.Position.CENTER)){
            x = windowSize.getWidth() / 2 - size.getWidth() / 2;
        }
        if(alignment.getHorizontal().equals(Alignment.Position.END)){
            x = windowSize.getWidth() - size.getWidth() - margin.getRight();
        }
        int y = padding.getTop();
        if(alignment.getVertical().equals(Alignment.Position.CENTER)){
            y = windowSize.getHeight() / 2 - size.getHeight() / 2;
        }
        if(alignment.getVertical().equals(Alignment.Position.END)){
            y = windowSize.getHeight() - size.getHeight() - margin.getBottom();
        }
        this.position = new Position(x, y);
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

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }
}
