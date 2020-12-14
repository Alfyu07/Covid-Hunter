package state.menu.ui;

import core.Size;
import game.settings.GameSettings;
import state.State;
import state.menu.MenuState;
import ui.*;
import ui.clickable.UIButton;
import ui.clickable.UISlider;

import java.awt.*;

public class UIOptionMenu extends VerticalContainer {

    private UISlider musicVolSlider;
    private UISlider soundVolSlider;
    private UIText musicVolLabel;
    private UIText soundVolLabel;

    public UIOptionMenu(Size windowSize, GameSettings gameSettings) {
        super(windowSize);
        setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER));

        musicVolSlider = new UISlider(0,1);
        musicVolSlider.setValue(gameSettings.getAudioSettings().getMusicVolume());

        soundVolSlider = new UISlider(0,1);
        soundVolSlider.setValue(gameSettings.getAudioSettings().getSoundVolume());
        soundVolSlider.setMargin(new Spacing(0,0,30,0));

        musicVolLabel = new UIText("");
        musicVolLabel.setMargin(new Spacing(10));
        soundVolLabel = new UIText("");
        soundVolLabel.setMargin(new Spacing(10));

        UIContainer labelContainer = new VerticalContainer(windowSize);
        labelContainer.setBackgroundColor(Color.darkGray);
        UIText title = new UIText("OPTIONS");
        title.setMargin(new Spacing(10));
        labelContainer.addUIComponent(title);

        UIContainer contentContainer = new VerticalContainer(windowSize);
        contentContainer.setBackgroundColor(Color.DARK_GRAY);
        contentContainer.addUIComponent(musicVolLabel);
        contentContainer.addUIComponent(musicVolSlider);
        contentContainer.addUIComponent(soundVolLabel);
        contentContainer.addUIComponent(soundVolSlider);
        contentContainer.addUIComponent(new UIButton("BACK", (state) -> ((MenuState) state).enterMenu(new UIMainMenu(windowSize))));

        addUIComponent(labelContainer);
        addUIComponent(contentContainer);



    }

    @Override
    public void update(State state) {
        super.update(state);
        handleVolume(state);

    }

    private void handleVolume(State state) {
        state.getGameSettings().getAudioSettings().setMusicVolume((float) musicVolSlider.getValue());
        musicVolLabel.setText(String.format("MUSIC VOL : %d", Math.round(musicVolSlider.getValue() * 100)));


        state.getGameSettings().getAudioSettings().setSoundVolume((float) soundVolSlider.getValue());
        soundVolLabel.setText(String.format("SOUND VOL : %d", Math.round(soundVolSlider.getValue() * 100)));
    }
}
