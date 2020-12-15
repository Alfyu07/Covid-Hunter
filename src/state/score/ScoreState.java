package state.score;

import core.Size;
import game.settings.GameSettings;
import input.Input;
import io.PersistableIO;
import map.GameMap;
import state.State;

import java.io.File;
import java.io.IOException;

public class ScoreState extends State {

    private ScoreList scoreList;

    public ScoreState(Size windowSize, Input input, GameSettings gameSettings, Score score) {
        super(windowSize, input, gameSettings);
        gameMap = new GameMap(new Size(15, 10), spriteLibrary);
        loadScores();
        scoreList.add(score);
        saveScores();

        uiContainers.add(new UIHighScore(windowSize, scoreList));
    }

    private void loadScores() {
        String scoreFilePath = ScoreState.class.getResource("/").getFile() + "score.text";
        if(new File(scoreFilePath).exists()){
            scoreList = PersistableIO.load(ScoreList.class, scoreFilePath);
        }else{
            scoreList = new ScoreList();
        }
    }

    private void saveScores(){
        File scoreFile = new File(ScoreState.class.getResource("/").getFile() + "score.text");
        try{
            scoreFile.createNewFile();
            PersistableIO.save(scoreList, scoreFile.getPath());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
