package state.score;

import core.Size;
import state.menu.MenuState;
import ui.*;
import ui.clickable.UIButton;

public class UIHighScore extends VerticalContainer {

    public UIHighScore(Size windowSize, ScoreList scoreList){
        super(windowSize);
        setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER));
        addUIComponent(new UIText("HIGH SCORE"));

        UIContainer scoreListContainer = new VerticalContainer(windowSize);
        for(Score score : scoreList.getTopTen()){
            scoreListContainer.addUIComponent(createScoreRow(score));
        }

        addUIComponent(scoreListContainer);
        addUIComponent(new UIButton("Menu", state -> state.setNextState(new MenuState(state.getWindowSize(), state.getInput(), state.getGameSettings())) ));
    }

    private UIComponent createScoreRow(Score score) {
        UIContainer scoreRow = new HorizontalContainer(windowSize);
        UIText timeStamp = new UIText(score.getFormattedTimeStamp());
        timeStamp.setMargin(new Spacing(5));
        UIText scoreUI = new UIText(String.valueOf(score.getScore()));
        scoreUI.setMargin(new Spacing(5));

        scoreRow.addUIComponent(timeStamp);
        scoreRow.addUIComponent(scoreUI);
        return scoreRow;
    }
}
