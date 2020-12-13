package game.ui;

import core.Size;
import game.state.GameState;
import game.state.State;
import ui.*;

public class UISicknessStatistics extends HorizontalContainer {
    private UIText numberOfSick;
    private UIText numberOfHealthy;

    public UISicknessStatistics(Size windowSize) {
        super(windowSize);
        this.numberOfHealthy = new UIText("");
        this.numberOfSick = new UIText("");
        this.setAlignment(new Alignment(Alignment.Position.START, Alignment.Position.START));
        UIContainer sickContainer = new VerticalContainer(windowSize);
        sickContainer.setPadding(new Spacing(0));
        sickContainer.addUIComponent(new UIText("SICK"));
        sickContainer.addUIComponent(numberOfSick);

        UIContainer healthyContainer = new VerticalContainer(windowSize);
        healthyContainer.setPadding(new Spacing(0));
        healthyContainer.addUIComponent(new UIText("HEALTHY"));
        healthyContainer.addUIComponent(numberOfHealthy);

        addUIComponent(healthyContainer);
        addUIComponent(sickContainer);
    }

    @Override
    public void update(State state){
        super.update(state);
        if(state instanceof GameState){
            GameState gameState = (GameState) state;
            numberOfSick.setText(String.format("%d (%d)", gameState.getNumberOfSick(), gameState.getNumberOfIsolated()));
            numberOfHealthy.setText(String.valueOf(gameState.getNumberOfHealthy()));
        }

    }

}
