package ui;

import core.Position;
import core.Size;

public class VerticalContainer extends UIContainer{

    @Override
    protected Size calculateContentSize() {
        int combinedChildHeight = 0;
        int widestChildWidth = 0;

        for (UIComponent uiComponent : child){
            combinedChildHeight += uiComponent.getSize().getHeight() +
                    uiComponent.getMargin().getVertical();

            if(uiComponent.getSize().getWidth() > widestChildWidth){
                widestChildWidth = uiComponent.getSize().getWidth();
            }
        }
        return new Size(widestChildWidth, combinedChildHeight);
    }

    @Override
    protected void calculatedContentPosition() {
        int currentY = padding.getTop();

        for (UIComponent uiComponent : child){
            currentY += uiComponent.getMargin().getTop();
            //setPosition to top left of the object
            uiComponent.setPosition(new Position(padding.getLeft(), currentY));
            currentY += uiComponent.getSize().getHeight();
            currentY += uiComponent.getMargin().getBottom();
        }
    }
}
