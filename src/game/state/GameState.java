package game.state;

import controller.NPCController;
import controller.PlayerController;
import core.Size;
import entities.NPC;
import entities.Player;
import entities.action.Cough;
import entities.effect.Sick;
import input.Input;
import map.GameMap;
import ui.HorizontalContainer;
import ui.Spacing;
import ui.UIContainer;
import ui.VerticalContainer;

import java.awt.*;

public class GameState extends State{

    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
        gameMap = new GameMap(new Size(20,20) ,spriteLibrary);
        initializeCharacter();
        initialzeUI();
    }

    private void initialzeUI() {
        UIContainer container = new VerticalContainer();
        container.setPadding(new Spacing(50));
        container.setBackgroundColor(Color.GRAY);
        container.addUIComponent(new HorizontalContainer());
        container.addUIComponent(new HorizontalContainer());
        container.addUIComponent(new HorizontalContainer());
        uiContainers.add(container);
    }

    private void initializeCharacter() {
        Player player = new Player(new PlayerController(input), spriteLibrary);
        gameObjects.add(player);
        camera.focusOn(player);
        initializeNPC(100);

    }

    private void initializeNPC(int numberOfNPC) {
        for(int i = 0; i< numberOfNPC; i++){
            NPC npc = new NPC(new NPCController(), spriteLibrary);
            npc.setPosition(gameMap.getRandomPosition());
            npc.perform(new Cough());
            
            npc.addEffect(new Sick());
            gameObjects.add(npc);

        }

    }


}
