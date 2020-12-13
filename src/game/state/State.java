package game.state;

import core.Position;
import core.Size;
import display.Camera;
import entities.GameObject;
import game.Time;
import gfx.SpriteLibrary;
import input.Input;
import map.GameMap;
import ui.UIContainer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class State {

    protected GameMap gameMap;
    protected List<GameObject> gameObjects;
    protected List<UIContainer> uiContainers;
    protected SpriteLibrary spriteLibrary;
    protected Input input;
    protected Camera camera;
    protected Time time;

    public State(Size windowSize, Input input) {
        this.input = input;
        gameObjects = new ArrayList<>();
        uiContainers = new ArrayList<>();
        spriteLibrary = new SpriteLibrary();
        camera = new Camera(windowSize);
        time = new Time();
    }

    public void update() {
        time.update();
        sortObjectsByPosition();
        updateGameObject();
        uiContainers.forEach(uiContainer -> uiContainer.update(this));
        camera.update(this);
        handleMouseInput();
    }

    private void handleMouseInput() {
        if(input.isMouseClicked()){
            System.out.println(String.format("MOUSE CLICKED AT POSITION (%d, %d)", input.getMousePosition().intX(), input.getMousePosition().intY()));
        }

        input.clearMouseCLick();
    }

    private void updateGameObject() {
        for(int i = 0; i<gameObjects.size(); i++){
            gameObjects.get(i).update(this);
        }
    }

    private void sortObjectsByPosition() {
        gameObjects.sort(Comparator.comparing(GameObject::getRenderOrder)
                .thenComparing(gameObject -> gameObject.getPosition().getY()));
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public Camera getCamera() {
        return camera;
    }

    public Time getTime() {
        return time;
    }

    public Position getRandomPosition() {
        return gameMap.getRandomPosition();
    }

    public List<GameObject> getCollidingGameObjects(GameObject gameObject) {
        return gameObjects.stream()
                .filter(other -> other.collidesWith(gameObject))
                .collect(Collectors.toList());
    }

    public List<UIContainer> getUIContainers() {
        return uiContainers;
    }

    public <T extends GameObject> List<T> getGameObjectsOfClass(Class<T> clazz){
        return gameObjects.stream()
                .filter(gameObject -> clazz.isInstance(gameObject))
                .map(gameObject -> (T) gameObject)
                .collect(Collectors.toList());
    }

    public SpriteLibrary getSpriteLibrary() {
        return spriteLibrary;
    }

    public void spawn(GameObject gameObject) {
        gameObjects.add(gameObject);
    }
}
