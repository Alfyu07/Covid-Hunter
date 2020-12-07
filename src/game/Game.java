package game;

import controller.PlayerController;
import display.Display;
import entities.GameObject;
import entities.Player;
import gfx.SpriteLibrary;
import input.Input;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private Display display;
    private List<GameObject> gameObjects; //player sama enemy
    private Input input;

    private SpriteLibrary spriteLibrary;

    public Game(int width, int height) {
        input = new Input();
        display = new Display(width, height,input);
        gameObjects = new ArrayList<>();
        gameObjects.add(new Player(new PlayerController(input)));
        spriteLibrary = new SpriteLibrary();
    }

    public void update(){
        gameObjects.forEach(gameObject -> gameObject.update());
    }

    public void render(){
        display.render(this);
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

}
