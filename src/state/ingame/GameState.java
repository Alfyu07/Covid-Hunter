package state.ingame;

import controller.NPCController;
import controller.PlayerController;
import core.Condition;
import core.Size;
import entities.NPC;
import entities.Player;
import entities.SelectionCircle;
import entities.humanoid.effect.Isolated;
import entities.humanoid.effect.Sick;
import game.Game;
import game.Timer;
import game.settings.GameSettings;
import state.ingame.ui.UIGameTime;
import state.ingame.ui.UIScore;
import state.ingame.ui.UISicknessStatistics;
import input.Input;
import map.GameMap;
import state.State;
import state.menu.MenuState;
import state.score.Score;
import state.score.ScoreState;
import ui.Alignment;
import ui.Spacing;
import ui.UIText;
import ui.VerticalContainer;
import ui.clickable.UIButton;

import java.awt.*;
import java.util.List;

public class GameState extends State {

    private List<Condition> victoryConditions;
    private List<Condition> defeatConditions;
    private boolean playing;
    private Timer gameTimer;
    private int score;

    public GameState(Size windowSize, Input input, GameSettings gameSettings) {
        super(windowSize, input, gameSettings);
        gameMap = new GameMap(new Size(15, 10), spriteLibrary);
        playing = true;
        gameTimer = new Timer(120, this::lose);

        initializeCharacters();
        initializeUI(windowSize);
        initializeConditions();

        audioPlayer.playMusic("/sounds/isobubbler.wav");

    }

    private void initializeConditions() {
        victoryConditions = List.of(() -> getNumberOfSick() == 0);
        defeatConditions = List.of(() -> (float) getNumberOfSick() / getNumberOfNPCs() > 0.25);
    }

    private void initializeUI(Size windowSize) {
        uiContainers.add(new UIGameTime(windowSize));
        uiContainers.add(new UISicknessStatistics(windowSize));
        uiContainers.add(new UIScore(windowSize));
    }

    private void initializeCharacters() {
        SelectionCircle circle = new SelectionCircle();
        Player player = new Player(new PlayerController(input), spriteLibrary, circle);
        gameObjects.add(player);
        camera.focusOn(player);

        gameObjects.add(circle);

        initializeNPCs(50);
        makeNumberOfNPCsSick(5);
    }

    private void makeNumberOfNPCsSick(int number) {
        getGameObjectsOfClass(NPC.class).stream()
                .limit(number)
                .forEach(npc -> npc.addEffect(new Sick()));
    }

    private void initializeNPCs(int numberOfNPCs) {
        for(int i = 0; i < numberOfNPCs; i++) {
            NPC npc = new NPC(new NPCController(), spriteLibrary);
            npc.setPosition(gameMap.getRandomPosition());
            gameObjects.add(npc);
        }
    }

    @Override
    public void update(Game game) {
        super.update(game);
        gameTimer.update();

        if(playing) {
            if(victoryConditions.stream().allMatch(Condition::isMet)) {
                win();
            }

            if(defeatConditions.stream().allMatch(Condition::isMet)) {
                lose();
            }
        }
    }

    private void lose() {
        playing = false;

        //Create UI
        VerticalContainer winContainer = new VerticalContainer(camera.getSize());
        winContainer.setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.START));
        UIText victory = new UIText("DEFEAT");
        victory.setMargin(new Spacing(200));
        winContainer.addUIComponent(victory);

        VerticalContainer menuContainer = new VerticalContainer(camera.getSize());
        menuContainer.setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER));
        menuContainer.setBackgroundColor(Color.DARK_GRAY);
        menuContainer.addUIComponent(new UIButton("Finished", (state) -> state.setNextState(new ScoreState(windowSize,
                input,
                gameSettings,
                Score.createNew(((GameState)state).getScore())
        ))));
        menuContainer.addUIComponent(new UIButton("Exit", (state) -> System.exit(0)));
        uiContainers.add(winContainer);
        uiContainers.add(menuContainer);
    }

    private void win() {
        playing = false;
        applyToScore(gameTimer.asSeconds() * 100);

        //Create UI
        VerticalContainer winContainer = new VerticalContainer(camera.getSize());
        winContainer.setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.START));

        UIText victory = new UIText("VICTORY");
        victory.setMargin(new Spacing(200,0,10,0));
        UIText score = new UIText(String.format("YOUR SCORE : %d", getScore()));
        score.setMargin(new Spacing(5,0,0,0));
        winContainer.addUIComponent(victory);
        winContainer.addUIComponent(score);

        VerticalContainer menuContainer = new VerticalContainer(camera.getSize());
        menuContainer.setAlignment(new Alignment(Alignment.Position.CENTER, Alignment.Position.CENTER));
        menuContainer.setBackgroundColor(Color.DARK_GRAY);
        menuContainer.addUIComponent(new UIButton("Finished", (state) -> state.setNextState(new ScoreState(windowSize,
                input,
                gameSettings,
                Score.createNew(((GameState)state).getScore())
        ))));
        menuContainer.addUIComponent(new UIButton("Exit", (state) -> System.exit(0)));
        uiContainers.add(winContainer);
        uiContainers.add(menuContainer);
    }

    public void applyToScore(int amount) {
        score+=amount;
    }

    public long getNumberOfSick() {
        return getGameObjectsOfClass(NPC.class).stream()
                .filter(npc -> npc.isAffectedBy(Sick.class) && !npc.isAffectedBy(Isolated.class))
                .count();
    }

    public long getNumberOfIsolated() {
        return getGameObjectsOfClass(NPC.class).stream()
                .filter(npc -> npc.isAffectedBy(Sick.class) && npc.isAffectedBy(Isolated.class))
                .count();
    }

    public long getNumberOfHealthy() {
        return getGameObjectsOfClass(NPC.class).stream()
                .filter(npc -> !npc.isAffectedBy(Sick.class))
                .count();
    }

    public long getNumberOfNPCs() {
        return getGameObjectsOfClass(NPC.class).size();
    }

    public Timer getGameTimer() {
        return gameTimer;
    }

    public int getScore() {
        return score;
    }
}

