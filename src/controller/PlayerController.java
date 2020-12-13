package controller;

import input.Input;
import java.awt.event.KeyEvent;
/*
* class untuk mengatur control player.
* */

public class PlayerController implements EntityController {

    private Input input;

    public PlayerController(Input input){
        this.input = input;
    }

    @Override
    public boolean isRequestingUp() {
        return input.isCurrentlyPressed(KeyEvent.VK_W);
    }

    @Override
    public boolean isRequestingDown() {
        return input.isCurrentlyPressed(KeyEvent.VK_S);
    }

    @Override
    public boolean isRequestingLeft() {
        return input.isCurrentlyPressed(KeyEvent.VK_A);
    }

    @Override
    public boolean isRequestingRight() {
        return input.isCurrentlyPressed(KeyEvent.VK_D);
    }
}
