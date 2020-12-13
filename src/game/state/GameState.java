package game.state;

import controller.NPCController;
import controller.PlayerController;
import core.Size;
import entities.NPC;
import entities.Player;
import entities.effect.Sick;
import game.ui.UIGameTime;
import game.ui.UISicknessStatistics;
import input.Input;
import map.GameMap;

public class GameState extends State {

    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
        gameMap = new GameMap(new Size(20, 20), spriteLibrary);
        initializeCharacters();
        initializeUI(windowSize);
    }

    private void initializeUI(Size windowSize) {
        uiContainers.add(new UIGameTime(windowSize));
        uiContainers.add(new UISicknessStatistics(windowSize));
    }

    private void initializeCharacters() {
        Player player = new Player(new PlayerController(input), spriteLibrary);
        gameObjects.add(player);
        camera.focusOn(player);

        initializeNPCs(100);
        makeNumberofNPCSick(10);
    }

    private void makeNumberofNPCSick(int number) {
        getGameObjectsOfClass(NPC.class).stream()
                .limit(number)
                .forEach(npc -> npc.addEffect(new Sick()));
    }

    private void initializeNPCs(int numberOfNPCs) {
        for(int i = 0; i < numberOfNPCs; i++) {
            NPC npc = new NPC(new NPCController(), spriteLibrary);
            npc.setPosition(gameMap.getRandomPosition());

            //make them all sick
//            npc.addEffect(new Sick());
            gameObjects.add(npc);
        }
    }
}
