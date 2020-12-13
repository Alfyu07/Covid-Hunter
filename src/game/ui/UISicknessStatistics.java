package game.ui;

import core.Size;
import entities.MovingEntity;
import entities.humanoid.Humanoid;
import entities.humanoid.effect.Sick;
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
        long sickCount = state.getGameObjectsOfClass(Humanoid.class).stream()
                .filter(humanoid -> humanoid.isAffectedBy(Sick.class))
                .count();
        long healthyCount = state.getGameObjectsOfClass(Humanoid.class).stream()
                .filter(humanoid -> !humanoid.isAffectedBy(Sick.class))
                .count();

        numberOfSick.setText(String.valueOf(sickCount));
        numberOfHealthy.setText(String.valueOf(healthyCount));
    }

}
