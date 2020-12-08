package game.state;

import controller.PlayerController;
import core.Size;
import entities.Player;
import input.Input;
import map.GameMap;

public class GameState extends State{

    public GameState(Input input) {
        super(input);
        gameMap = new GameMap(new Size(20,20) ,spriteLibrary);
        gameObjects.add(new Player(new PlayerController(input), spriteLibrary));
    }
}
