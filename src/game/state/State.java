package game.state;

import controller.PlayerController;
import core.Size;
import entities.GameObject;
import gfx.SpriteLibrary;
import input.Input;
import map.GameMap;

import java.util.ArrayList;
import java.util.List;

public abstract class State {
    protected GameMap gameMap;
    protected List<GameObject> gameObjects; //player sama enemy
    protected SpriteLibrary spriteLibrary;
    protected Input input;

    public State(Input input){
        this.input = input;
        gameObjects = new ArrayList<>();
        spriteLibrary = new SpriteLibrary();
    }
    public void update(){
        gameObjects.forEach(gameObject -> gameObject.update());
    }
    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public GameMap getGameMap() {
        return gameMap;
    }
}
