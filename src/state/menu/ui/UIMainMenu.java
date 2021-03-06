package state.menu.ui;

import core.Size;
import state.ingame.GameState;
import state.menu.MenuState;
import ui.Alignment;
import ui.Spacing;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UIButton;

public class UIMainMenu extends VerticalContainer {
    public UIMainMenu(Size windowSize) {
        super(windowSize);
        setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER));
        UIText title = new UIText("COVID HUNTER");
        title.setMargin(new Spacing(10));
        addUIComponent(title);
        addUIComponent(new UIButton("Play", (state) -> state.setNextState(new GameState(windowSize,state.getInput(), state.getGameSettings()))));
        addUIComponent(new UIButton("OPTIONS", (state) -> ((MenuState) state).enterMenu(new UIOptionMenu(windowSize, state.getGameSettings()))));
        addUIComponent(new UIButton("EXIT", (state)-> System.exit(0)));
    }
}
