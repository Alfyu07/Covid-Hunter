package controller;

import game.Game;
import input.Input;

import java.awt.event.KeyEvent;

public class GameController {
    private Input input;

    public GameController(Input input){
        this.input = input;
    }

    public void update(Game game){
        if(input.isPressed(KeyEvent.VK_F3)){
            game.getSettings().toggleDebugMode();
        }

        if(input.isPressed(KeyEvent.VK_PERIOD)){
            game.getSettings().increaseGameSpeed();
        }
        if(input.isPressed(KeyEvent.VK_COMMA)){
            game.getSettings().decreaseGameSpeed();
        }
    }
}
