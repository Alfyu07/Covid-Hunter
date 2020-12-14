package state.ingame.ui;

import core.Size;
import state.State;
import state.ingame.GameState;
import ui.Alignment;
import ui.UIText;
import ui.VerticalContainer;

public class UIScore extends VerticalContainer {

    private UIText score;
    public UIScore(Size windowSize) {
        super(windowSize);
        setAlignment(new Alignment(Alignment.Position.END, Alignment.Position.START));
        score = new UIText("0");

        addUIComponent(new UIText("SCORE"));
        addUIComponent(score);
    }

    @Override
    public void update(State state) {
        super.update(state);
        GameState gameState = (GameState) state;
        score.setText(String.valueOf(gameState.getScore()));
    }
}
