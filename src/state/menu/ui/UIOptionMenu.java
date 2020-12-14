package state.menu.ui;

import core.Size;
import state.menu.MenuState;
import ui.Alignment;
import ui.Spacing;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UIButton;

public class UIOptionMenu extends VerticalContainer {
    public UIOptionMenu(Size windowSize) {
        super(windowSize);

        UIText title = new UIText("OPTIONS");
        title.setMargin(new Spacing(10));
        addUIComponent(title);
        setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER));
        addUIComponent(new UIButton("Back", (state) -> ((MenuState) state).enterMenu(new UIMainMenu(windowSize))));
    }

}
