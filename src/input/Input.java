package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/*
    class untuk mengatur kondisi masukan pada keyboard,
 */

public class Input implements KeyListener {

    private boolean[] currentlyPressed;
    private boolean[] pressed;

    public Input(){
        currentlyPressed = new boolean[256];
        pressed = new boolean[256];
    }

    public boolean isPressed(int keyCode){
         if(!pressed[keyCode] && currentlyPressed[keyCode]){
            pressed[keyCode] = true;
            return true;
        }

        return false;
    }
    public boolean isCurrentlyPressed(int keyCode){
        return currentlyPressed[keyCode];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        currentlyPressed[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        currentlyPressed[e.getKeyCode()] = false;
        pressed[e.getKeyCode()] = false;
    }
}
