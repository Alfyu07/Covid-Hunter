package ui;

import core.Position;
import core.Size;

public class HorizontalContainer extends UIContainer{
    public HorizontalContainer(Size windowSize) {
        super(windowSize);
    }

    @Override
    protected Size calculateContentSize() {
        int combinedChildWith = 0;
        int tallesChiledHeihgt = 0;

        for (UIComponent uiComponent : child){
            combinedChildWith += uiComponent.getSize().getWidth() +
                    uiComponent.getMargin().getHorizontal();

            if(uiComponent.getSize().getHeight() > tallesChiledHeihgt){
                tallesChiledHeihgt = uiComponent.getSize().getHeight();
            }
        }
        return new Size(combinedChildWith, tallesChiledHeihgt);
    }

    @Override
    protected void calculatedContentPosition() {
        int currentX = padding.getLeft();

        for (UIComponent uiComponent : child){
            currentX += uiComponent.getMargin().getLeft();
            //setPosition to top left of the object
            uiComponent.setPosition(new Position(currentX, padding.getTop()));
            currentX += uiComponent.getSize().getWidth();
            currentX += uiComponent.getMargin().getRight();
        }
    }
}
