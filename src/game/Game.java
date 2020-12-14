package game;

import controller.GameController;
import core.Size;
import display.Display;
import game.settings.GameSetting;
import state.ingame.GameState;
import state.State;
import input.Input;
import state.menu.MenuState;

public class Game {

    public static final int SPRITE_SIZE = 64;

    private Display display;
    private Input input;
    private State state;
    private GameSetting settings;
    private GameController gameController;

    public Game(int width, int height) {
        input = new Input();
        display = new Display(width, height,input);
        state = new MenuState(new Size(width,height), input);
        settings = new GameSetting(false);
        gameController = new GameController(input);
    }

    public void update(){
        state.update(this);
        gameController.update(this);
    }

    public void render(){
        display.render(state, settings.isDebugMode());
    }


    public GameSetting getSettings() {
        return settings;
    }

    public void enterState(State nextState) {
        state = nextState;
    }
}
