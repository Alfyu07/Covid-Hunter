package game.state;

import controller.NPCController;
import controller.PlayerController;
import core.Size;
import entities.NPC;
import entities.Player;
import input.Input;
import map.GameMap;

public class GameState extends State{

    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
        gameMap = new GameMap(new Size(20,20) ,spriteLibrary);
        initializeCharacter();
        initializeNPC(100);
    }

    private void initializeCharacter() {
        Player player = new Player(new PlayerController(input), spriteLibrary);
        gameObjects.add(player);
        camera.focusOn(player);
    }

    private void initializeNPC(int numberOfNPC) {
        for(int i = 0; i< numberOfNPC; i++){
            NPC npc = new NPC(new NPCController(), spriteLibrary);
            npc.setPosition(gameMap.getRandomPosition());
            gameObjects.add(npc);

        }

    }


}
